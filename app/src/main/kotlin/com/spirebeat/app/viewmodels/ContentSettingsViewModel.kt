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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spirebeat.app.db.MusicDatabase
import com.spirebeat.app.lyrics.LyricsHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.spirebeat.app.paxsenix.PaxsenixLyrics
import com.spirebeat.app.paxsenix.models.PaxsenixStats
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

sealed interface PaxsenixStatsState {
    data object Loading : PaxsenixStatsState
    data class Success(val stats: PaxsenixStats) : PaxsenixStatsState
    data object Error : PaxsenixStatsState
}

@HiltViewModel
class ContentSettingsViewModel @Inject constructor(
    private val lyricsHelper: LyricsHelper,
    private val database: MusicDatabase,
) : ViewModel() {

    private val _paxsenixStatsState = MutableStateFlow<PaxsenixStatsState>(PaxsenixStatsState.Loading)
    val paxsenixStatsState = _paxsenixStatsState.asStateFlow()

    fun fetchPaxsenixStats() {
        _paxsenixStatsState.value = PaxsenixStatsState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            PaxsenixLyrics.getStats()
                .onSuccess { _paxsenixStatsState.value = PaxsenixStatsState.Success(it) }
                .onFailure { _paxsenixStatsState.value = PaxsenixStatsState.Error }
        }
    }

    fun clearLyricsCache() {
        viewModelScope.launch(Dispatchers.IO) {
            lyricsHelper.clearCache()
            database.query {
                clearAllLyrics()
            }
        }
    }
}
