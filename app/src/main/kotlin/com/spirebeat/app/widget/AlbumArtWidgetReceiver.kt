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

package com.spirebeat.app.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AlbumArtWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget = AlbumArtWidget()

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun onEnabled(context: Context) {
        super.onEnabled(context)
        scope.launch {
            requestPlaybackWidgetUpdate(context)
        }
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        scope.launch {
            requestPlaybackWidgetUpdate(context)
        }
    }
}
