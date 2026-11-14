<div align="center">

  <img src="https://raw.githubusercontent.com/aayushpokhrel49/spirebeat/main/fastlane/metadata/android/en-US/images/icon.png" width="160" height="160" alt="SpireBeat Logo" style="border-radius: 22%">

  <h1>SpireBeat</h1>

  <p align="center">
    <strong>Redefining the YouTube Music experience on Android.</strong>
    <br />
    <em>High-performance, privacy-focused, and packed with features for people who really care about their music.</em>
  </p>

  <p align="center">
    <a href="https://github.com/aayushpokhrel49/spirebeat"><b>Repository</b></a> •
    <a href="#features"><b>Features</b></a> •
    <a href="#download"><b>Download</b></a> •
    <a href="#build-from-source"><b>Build from source</b></a> •
    <a href="https://github.com/aayushpokhrel49/spirebeat/blob/main/PRIVACY.md"><b>Privacy</b></a> •
    <a href="https://github.com/aayushpokhrel49/spirebeat/issues/new/choose"><b>Support</b></a>
  </p>

  <div align="center">
    <img src="https://img.shields.io/github/v/release/aayushpokhrel49/spirebeat?style=for-the-badge&color=6366f1&labelColor=1e1e2e&logo=github" alt="Latest Version" />
    <img src="https://img.shields.io/github/downloads/aayushpokhrel49/spirebeat/total?style=for-the-badge&color=6366f1&labelColor=1e1e2e&logo=github" alt="Downloads" />
    <img src="https://img.shields.io/github/stars/aayushpokhrel49/spirebeat?style=for-the-badge&color=6366f1&labelColor=1e1e2e&logo=github" alt="Stars" />
    <img src="https://img.shields.io/github/license/aayushpokhrel49/spirebeat?style=for-the-badge&color=6366f1&labelColor=1e1e2e" alt="License" />
    <img src="https://img.shields.io/badge/Architecture-MVVM-6366f1?style=for-the-badge&labelColor=1e1e2e&logo=kotlin" alt="MVVM Architecture" />
    <img src="https://img.shields.io/badge/Language-Kotlin-7f52ff?style=for-the-badge&logo=kotlin&color=6366f1&labelColor=1e1e2e" alt="Kotlin" />
    <img src="https://img.shields.io/badge/Toolkit-Jetpack_Compose-4285f4?style=for-the-badge&logo=jetpack-compose&color=6366f1&labelColor=1e1e2e" alt="Jetpack Compose" />
    <img src="https://img.shields.io/badge/Design-Material_3-000000?style=for-the-badge&logo=material-design&color=6366f1&labelColor=1e1e2e" alt="Material 3" />
  </div>

  <br />

</div>

<hr />

**SpireBeat** isn't just another YouTube Music wrapper. It's a fully custom-built player, made from the ground up, because music should stay private, look clean, and work exactly the way you expect. Built on a modern Kotlin + Jetpack Compose stack, it is backed by a continuous integration pipeline that produces reproducible, signed APKs straight from GitHub Actions.

---

> [!IMPORTANT]
> **Geographic availability:** if YouTube Music is not supported in your region, a VPN or proxy set to a supported region is required for initial data fetching.

---

## ✨ Features

**Playback & library**
- Stream from YouTube Music with high-quality audio, HLS support, and background playback
- Playlists, albums, artists, radios (including "liked" radio), and queue management
- Downloads with chunked streams and a smart track selector
- Sleep timer, equalizer presets, AOD player, and playback resumption planning

**Lyrics**
- 12 lyric providers, orderable from settings: SimpMusic, BetterLyrics, Unison, LRC Lib, KuGou, Paxsenix (Apple Music / NetEase / Spotify / Musixmatch / KuGou), YouTube subtitles, and YouTube lyrics
- Synced line/word lyrics with styling, and optional AI translation via OpenAI, Gemini, Claude, or OpenRouter

**Discovery & recognition**
- Browse Home, Explore, Charts, New Releases, Mood & Genres, and "Year in Music" rewind
- Built-in music recognition (Shazam-style fingerprinting) with a quick-settings tile
- Spotify import: bring in your Spotify library, playlists, liked songs, and recommendations

**Social & extras**
- **Together** — host or join synchronized listening sessions (on-device server or online)
- Discord Rich Presence via the official Social SDK
- **Canvas** — animated album artwork in the player
- Home-screen widgets: classic, album art, now-playing card, playback deck, capsule, spotlight, and command widgets
- Deep theming: dynamic color, custom seed palettes, pure-black mode, and per-screen tints
- Proxy support, IP rotation, and DNS-over-HTTPS for restricted networks

**Privacy**
- No ads, no tracking, no analytics. Your data stays on your device unless you opt into a service
- Full transparency in the [Privacy Notice](https://github.com/aayushpokhrel49/spirebeat/blob/main/PRIVACY.md)

---

## 📸 Showcase

Screenshots will be published with the first stable release.

---

## 📥 Download

| Channel | Link |
|---|---|
| **GitHub Releases** (signed, per-ABI) | [Releases](https://github.com/aayushpokhrel49/spirebeat/releases) |
| Nightly builds (dev branch) | [Nightly.link](https://nightly.link/aayushpokhrel49/spirebeat/workflows/build/dev/app-mobile-universal-release) |
| Obtainium | [Add repository](https://apps.obtainium.imranr.dev/redirect?r=obtainium://add/https://github.com/aayushpokhrel49/spirebeat/) |
| F-Droid / IzzyOnDroid | [IzzyOnDroid](https://apt.izzysoft.de/fdroid/index/apk/com.spirebeat.app) |
| OpenAPK | [OpenAPK](https://www.openapk.net/spirebeat/com.spirebeat.app/) |

**Which APK do I need?**

- `app-mobile-universal-release.apk` — all ABIs, largest file; recommended for broad compatibility
- `app-mobile-arm64-release.apk` — modern 64-bit devices (most phones after 2017)
- `app-mobile-armeabi-release.apk` — legacy 32-bit devices
- `app-mobile-x86_64-release.apk` / `app-mobile-x86-release.apk` — emulators / x86 devices
- `app-tv-universal-release.apk` — Android TV / Google TV

---

## ⚙️ Build from source

### Using GitHub Actions (recommended)

This repository ships a full CI pipeline — no local Android SDK needed.

1. Fork this repository.
2. Open **Actions** → the `Build APKs` workflow → **Run workflow** (`workflow_dispatch`).
3. When it finishes, download the APK artifacts from the run summary.
4. For **signed** release builds, configure these repository secrets:
   | Secret | Purpose |
   |---|---|
   | `KEYSTORE` | Base64-encoded signing keystore |
   | `KEY_ALIAS` | Keystore alias |
   | `KEYSTORE_PASSWORD` | Keystore password (also used as the key password) |
   | `LASTFM_API_KEY` / `LASTFM_SECRET` | Optional, Last.fm scrobbling |
   | `TOGETHER_BEARER_TOKEN` | Optional, Together online hosting |
   | `CANVAS_BEARER_TOKEN` | Optional, canvas artwork service |

   The `Release` workflow automatically detects a `versionName` change in `app/build.gradle.kts`, verifies the build is reproducible (two builds, identical SHA-256), builds all six APK variants, signs them, and publishes a GitHub Release with an auto-generated changelog.

### Building locally

**Requirements**

- JDK 21
- Android SDK (compileSdk 37)
- A machine with ~8 GB+ RAM (the app is large)

```bash
# Clone
git clone https://github.com/aayushpokhrel49/spirebeat.git
cd spirebeat

# Optional: put API keys in local.properties (all optional)
# LASTFM_API_KEY=...
# LASTFM_SECRET=...
# TOGETHER_BEARER_TOKEN=...
# CANVAS_BEARER_TOKEN=...

# Debug build (mobile, universal ABI)
./gradlew :app:assembleMobileUniversalDebug

# Release build (mobile, universal ABI)
./gradlew :app:assembleMobileUniversalRelease

# Unit tests
./gradlew :app:test
```

Build outputs land in `app/build/outputs/apk/`.

---

## 🤝 Acknowledgments

We stand on the shoulders of open-source giants:

- **InnerTune / ViMusic** — original InnerTube architecture this project builds upon
- **NewPipe Extractor** — stream cipher deobfuscation
- **Metrolist** by [Mostafa Alagamy](https://github.com/mostafaalagamy/Metrolist) — base framework
- **SimpMusic** by [maxrave-dev](https://github.com/maxrave-dev/SimpMusic) — lyrics API provider
- All the open-source libraries listed in `gradle/libs.versions.toml`

---

## 📄 License

```
SpireBeat (2026)
© Aayush Pokhrel — github.com/aayushpokhrel49/spirebeat
GPL-3.0 License | Contributors: see git history
```

This project is licensed under the [GNU General Public License v3.0](LICENSE).
