<div align="center">

  <img src="https://raw.githubusercontent.com/aayushpokhrel49/spirebeat/main/fastlane/metadata/android/en-US/images/icon.png" width="160" height="160" alt="SpireBeat ロゴ" style="border-radius: 22%">

  <h1>SpireBeat</h1>

  <p align="center">
    <strong>Android向け YouTube Music 体験を再定義する、プライバシー重視のミュージックプレイヤー。</strong>
  </p>

  <p align="center">
    <a href="https://github.com/aayushpokhrel49/spirebeat"><b>リポジトリ</b></a> •
    <a href="https://github.com/aayushpokhrel49/spirebeat/releases"><b>ダウンロード</b></a> •
    <a href="https://github.com/aayushpokhrel49/spirebeat/blob/main/PRIVACY.md"><b>プライバシー</b></a> •
    <a href="https://github.com/aayushpokhrel49/spirebeat/issues/new/choose"><b>サポート</b></a>
  </p>

</div>

<hr />

**SpireBeat** は単なる YouTube Music のラッパーではありません。Kotlin + Jetpack Compose でゼロから構築された完全カスタムプレイヤーです。広告・トラッキングなし。すべてのビルドは GitHub Actions 上で生成・署名され、再現可能です。

---

> [!IMPORTANT]
> お住まいの地域で YouTube Music が利用できない場合、初期データ取得には対応地域の VPN / プロキシが必要です。

---

## ✨ 主な機能

- **再生とライブラリ** — YouTube Music のストリーミング、プレイリスト・アルバム・アーティスト・ラジオ、ダウンロード、スリープタイマー、イコライザー、AODプレイヤー
- **歌詞** — 12種類の歌詞プロバイダー(SimpMusic / BetterLyrics / Unison / LRC Lib / KuGou / Paxsenix 他)と、AI翻訳(OpenAI / Gemini / Claude / OpenRouter)
- **音楽認識** — オンデバイスの Shazam 方式フィンガープリント認識(クイック設定タイル付き)
- **Spotify インポート** — ライブラリ・プレイリスト・お気に入り・レコメンドを移行
- **Together** — 同期再生セッション(ホスト / オンライン)
- **Discord ステータス** — 公式 Social SDK による Rich Presence
- **Canvas** — アニメーション付きアルバムアートワーク
- **ウィジェット** — クラシック / アルバムアート / ナウプレイング / デッキ / カプセル など7種
- **テーマ** — ダイナミックカラー、カスタムパレット、ピュアブラック、スクリーン別ティント
- **ネットワーク** — プロキシ、IPローテーション、DNS-over-HTTPS

---

## 📥 ダウンロード

| チャンネル | リンク |
|---|---|
| **GitHub Releases**(署名済み APK) | [Releases](https://github.com/aayushpokhrel49/spirebeat/releases) |
| ナイトリービルド(mainブランチ) | [Nightly.link](https://nightly.link/aayushpokhrel49/spirebeat/workflows/build/main/app-mobile-universal-release) |
| Obtainium | [リポジトリ追加](https://apps.obtainium.imranr.dev/redirect?r=obtainium://add/https://github.com/aayushpokhrel49/spirebeat/) |
| IzzyOnDroid | [IzzyOnDroid](https://apt.izzysoft.de/fdroid/index/apk/com.spirebeat.app) |

一般的な端末には `app-mobile-universal-release.apk`(または `app-mobile-arm64-release.apk`)を、Android TV には `app-tv-universal-release.apk` をお使いください。

---

## ⚙️ ビルド

### GitHub Actions(推奨)

このリポジトリには CI パイプラインが同梱されており、Android SDK なしでビルドできます。

1. このリポジトリをフォーク
2. **Actions** → `Build APKs` → **Run workflow**
3. 完了後、成果物(APK)をダウンロード

署名付きリリースをビルドするには、シークレット `KEYSTORE` / `KEY_ALIAS` / `KEYSTORE_PASSWORD` / `KEY_PASSWORD` と、任意で `LASTFM_API_KEY` / `LASTFM_SECRET` / `TOGETHER_BEARER_TOKEN` / `CANVAS_BEARER_TOKEN` を設定してください。`main` へのプッシュが成功するたびに、6種のAPKのビルド・署名・GitHub Release の自動作成が行われます。新しいバージョンを公開するには `app/build.gradle.kts` の `versionName` を更新してください。

### ローカルビルド

**要件**: JDK 21、Android SDK(compileSdk 37)

```bash
git clone https://github.com/aayushpokhrel49/spirebeat.git
cd spirebeat
./gradlew :app:assembleMobileUniversalDebug   # デバッグビルド
./gradlew :app:assembleMobileUniversalRelease  # リリースビルド
./gradlew :app:test                            # 単体テスト
```

出力先: `app/build/outputs/apk/`

---

## 📄 ライセンス

GNU General Public License v3.0 (GPL-3.0)

```
SpireBeat (2026)
© Aayush Pokhrel — github.com/aayushpokhrel49/spirebeat
GPL-3.0 License | Contributors: see git history
```

詳細な機能・スクリーンショット・謝辞は英語版 [README.md](README.md) を参照してください。
