/*
 * SpireBeat (2026)
 * © Aayush Pokhrel — github.com/aayushpokhrel49/spirebeat
 * GPL-3.0 License | Contributors: see git history
 * Do not remove or alter this notice. - Per GPL-3.0 Section 4 & Section 5
 */

/*
 * SpireBeat (2026)
 * © Aayush Pokhrel — github.com/aayushpokhrel49/spirebeat
 * GPL-3.0 License | Contributors: see git history
 * Do not remove or alter this notice. - Per GPL-3.0 Section 4 & Section 5
 */





package com.spirebeat.app.viewmodels

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import com.spirebeat.app.R
import com.spirebeat.app.ai.AiLyricsTranslator
import com.spirebeat.app.ai.AiServiceConfig
import com.spirebeat.app.constants.AiApiKeyKey
import com.spirebeat.app.constants.AiApiValidationStatus
import com.spirebeat.app.constants.AiApiValidationStatusKey
import com.spirebeat.app.constants.AiCustomEndpointKey
import com.spirebeat.app.constants.AiCustomModelKey
import com.spirebeat.app.constants.AiProvider
import com.spirebeat.app.constants.AiProviderKey
import com.spirebeat.app.constants.AiSelectedModelKey
import com.spirebeat.app.constants.TranslatorTargetLangKey
import com.spirebeat.app.db.MusicDatabase
import com.spirebeat.app.db.entities.LyricsEntity
import com.spirebeat.app.extensions.toEnum
import com.spirebeat.app.lyrics.LyricsHelper
import com.spirebeat.app.lyrics.LyricsResult
import com.spirebeat.app.models.MediaMetadata
import com.spirebeat.app.utils.NetworkConnectivityObserver
import com.spirebeat.app.utils.dataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
 
import javax.inject.Inject

@HiltViewModel
class LyricsMenuViewModel
@Inject
constructor(
    @ApplicationContext private val context: Context,
    private val lyricsHelper: LyricsHelper,
    val database: MusicDatabase,
    private val networkConnectivity: NetworkConnectivityObserver,
) : ViewModel() {
    private var job: Job? = null
    val results = MutableStateFlow(emptyList<LyricsResult>())
    val isLoading = MutableStateFlow(false)
    val isRefetching = MutableStateFlow(false)
    val isAiTranslating = MutableStateFlow(false)

    private val _aiTranslationEvents = MutableSharedFlow<String>()
    val aiTranslationEvents: SharedFlow<String> = _aiTranslationEvents.asSharedFlow()

    private val _isNetworkAvailable = MutableStateFlow(false)
    val isNetworkAvailable: StateFlow<Boolean> = _isNetworkAvailable.asStateFlow()

    init {
        viewModelScope.launch {
            networkConnectivity.networkStatus.collect { isConnected ->
                _isNetworkAvailable.value = isConnected
            }
        }
        
        // Set initial state using synchronous check
        _isNetworkAvailable.value = try {
            networkConnectivity.isCurrentlyConnected()
        } catch (e: Exception) {
            true // Assume connected as fallback
        }
    }

    fun search(
        mediaId: String,
        title: String,
        artist: String,
        album: String?,
        duration: Int,
    ) {
        isLoading.value = true
        results.value = emptyList()
        job?.cancel()
        job =
            viewModelScope.launch(Dispatchers.IO) {
                lyricsHelper.getAllLyrics(mediaId, title, artist, album, duration) { result ->
                    results.update {
                        it + result
                    }
                }
                isLoading.value = false
            }
    }

    fun cancelSearch() {
        job?.cancel()
        job = null
    }

    fun refetchLyrics(
        mediaMetadata: MediaMetadata,
        lyricsEntity: LyricsEntity?,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            isRefetching.value = true
            try {
                val lyrics = lyricsHelper.getLyrics(mediaMetadata)
                database.query {
                    lyricsEntity?.let(::delete)
                    upsert(LyricsEntity(mediaMetadata.id, lyrics))
                }
            } catch (_: Exception) {
            } finally {
                isRefetching.value = false
            }
        }
    }

    fun updateLyrics(
        mediaMetadata: MediaMetadata,
        lyrics: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            database.query {
                upsert(LyricsEntity(mediaMetadata.id, lyrics))
            }
        }
    }

    fun translateLyricsWithAi(
        mediaMetadata: MediaMetadata,
        lyrics: String,
    ) {
        if (isAiTranslating.value || lyrics.isBlank()) return
        viewModelScope.launch(Dispatchers.IO) {
            isAiTranslating.value = true
            try {
                val prefs = context.dataStore.data.first()
                val translatedLyrics = AiLyricsTranslator().translate(
                    config = AiServiceConfig(
                        provider = prefs[AiProviderKey].toEnum(AiProvider.NONE),
                        apiKey = prefs[AiApiKeyKey].orEmpty(),
                        customEndpoint = prefs[AiCustomEndpointKey].orEmpty(),
                        model = if (prefs[AiProviderKey].toEnum(AiProvider.NONE) == AiProvider.CUSTOM) {
                            prefs[AiCustomModelKey].orEmpty()
                        } else {
                            prefs[AiSelectedModelKey].orEmpty()
                        },
                    ),
                    lyrics = lyrics,
                    targetLanguage = prefs[TranslatorTargetLangKey].orEmpty().ifBlank { "ENGLISH" },
                )
                database.query {
                    upsert(LyricsEntity(mediaMetadata.id, translatedLyrics))
                }
                context.dataStore.edit { settings ->
                    settings[AiApiValidationStatusKey] = AiApiValidationStatus.SUCCESS.name
                }
                _aiTranslationEvents.emit(context.getString(R.string.translation_success))
            } catch (e: Exception) {
                context.dataStore.edit { settings ->
                    settings[AiApiValidationStatusKey] = AiApiValidationStatus.FAILED.name
                }
                _aiTranslationEvents.emit(
                    context.getString(R.string.translation_failed) + ": " + (e.localizedMessage ?: e.toString()),
                )
            } finally {
                isAiTranslating.value = false
            }
        }
    }
}
