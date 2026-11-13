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





package com.spirebeat.app.extensions

import java.io.File
import java.io.InputStream
import java.io.OutputStream
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

operator fun File.div(child: String): File = File(this, child)

fun File.directorySizeBytes(): Long {
    if (!exists()) return 0L
    return walkTopDown()
        .filter { it.isFile }
        .sumOf { it.length() }
}

fun InputStream.zipInputStream(): ZipInputStream = ZipInputStream(this)

fun OutputStream.zipOutputStream(): ZipOutputStream = ZipOutputStream(this)
