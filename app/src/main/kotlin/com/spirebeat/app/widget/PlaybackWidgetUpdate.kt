/*
 * SpireBeat (2026)
 * © Aayush Pokhrel — github.com/aayushpokhrel49/spirebeat
 * GPL-3.0 License | Contributors: see git history
 * Do not remove or alter this notice. - Per GPL-3.0 Section 4 & Section 5
 */

/*
 * SpireBeat (2026)
 * © Aayush Pokhrel - github.com/aayushpokhrel49/spirebeat
 * GPL-3.0 License | Contributors: see git history
 * Do not remove or alter this notice. - Per GPL-3.0 Section 4 & Section 5
 */

package com.spirebeat.app.widget

import android.content.ComponentName
import android.content.Context
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.spirebeat.app.playback.MusicService
import java.util.concurrent.TimeUnit

internal suspend fun requestPlaybackWidgetUpdate(context: Context) {
    try {
        val token = SessionToken(
            context,
            ComponentName(context, MusicService::class.java),
        )
        val future = MediaController.Builder(context, token).buildAsync()
        val controller = withContext(Dispatchers.IO) {
            future.get(2, TimeUnit.SECONDS)
        }

        try {
            val serviceIntent = android.content.Intent(context, MusicService::class.java)
            context.bindService(
                serviceIntent,
                object : android.content.ServiceConnection {
                    override fun onServiceConnected(name: ComponentName?, binder: android.os.IBinder?) {
                        val service = (binder as? MusicService.MusicBinder)?.service
                        service?.updateWidget()
                        runCatching { context.unbindService(this) }
                    }

                    override fun onServiceDisconnected(name: ComponentName?) = Unit
                },
                Context.BIND_AUTO_CREATE,
            )
        } finally {
            controller.release()
        }
    } catch (_: Exception) {
    }
}
