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

package com.spirebeat.app.playback

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class StreamChunkResolverTest {
    @Test
    fun clampsUnknownLengthToRemainingBytesNearEndOfStream() {
        val resolvedLength =
            resolveStreamChunkLength(
                requestedLength = -1L,
                position = 998L,
                knownContentLength = 1_000L,
                chunkLength = 512L,
            )

        assertEquals(2L, resolvedLength)
    }

    @Test
    fun keepsSmallerExplicitPlayerRequest() {
        val resolvedLength =
            resolveStreamChunkLength(
                requestedLength = 128L,
                position = 100L,
                knownContentLength = 1_000L,
                chunkLength = 512L,
            )

        assertEquals(128L, resolvedLength)
    }

    @Test
    fun returnsNullWhenChunkingCannotProducePositiveLength() {
        val resolvedLength =
            resolveStreamChunkLength(
                requestedLength = -1L,
                position = 1_000L,
                knownContentLength = 1_000L,
                chunkLength = 512L,
            )

        assertNull(resolvedLength)
    }
}
