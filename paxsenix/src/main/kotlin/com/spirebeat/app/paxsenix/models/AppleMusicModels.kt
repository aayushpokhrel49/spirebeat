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


package com.spirebeat.app.paxsenix.models

import kotlinx.serialization.Serializable

@Serializable
data class AppleMusicSearchItem(
    val id: String = "",
    val songName: String = "",
    val artistName: String = "",
    val duration: Int = 0
)

@Serializable
data class AppleMusicLyricsResponse(
    val type: String? = null,
    val content: List<AppleMusicLine> = emptyList()
)

@Serializable
data class AppleMusicLine(
    val timestamp: Long = 0,
    val text: List<AppleMusicWord> = emptyList()
)

@Serializable
data class AppleMusicWord(
    val text: String,
    val timestamp: Long? = null
)
