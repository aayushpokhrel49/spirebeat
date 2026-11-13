# Contributing to SpireBeat

Welcome! Thanks for taking the time to contribute — every star, bug report, translation, and pull request helps SpireBeat grow.

Please read our [Code of Conduct](CODE_OF_CONDUCT.md) before participating; by contributing you agree to abide by its terms.

---

## Table of contents

- [Development environment](#development-environment)
- [Building the app](#building-the-app)
- [Building with GitHub Actions](#building-with-github-actions)
- [Running tests](#running-tests)
- [Conventional commits](#conventional-commits)
- [Pull request workflow](#pull-request-workflow)
- [License headers](#license-headers)
- [Reporting issues](#reporting-issues)

---

## Development environment

Required tools:

- **JDK 21** (Temurin or equivalent)
- **Android SDK** with platforms up to `compileSdk 37`
- At least ~8 GB of RAM (the app is large; Gradle builds are memory-hungry)

Recommended environment variables / `local.properties` (all **optional**):

```properties
# app/build.gradle.kts reads these to embed API keys
LASTFM_API_KEY=
LASTFM_SECRET=
TOGETHER_BEARER_TOKEN=
CANVAS_BEARER_TOKEN=
```

The app builds fine without them — the features that use them simply ship with empty tokens.
`local.properties` and `~/.android/debug.keystore` are needed for local builds; the CI also generates a debug keystore automatically.

---

## Building the app

```bash
# Clone
git clone https://github.com/aayushpokhrel49/spirebeat.git
cd spirebeat

# Debug build — mobile, universal ABI (fastest full build)
./gradlew :app:assembleMobileUniversalDebug

# Release build — mobile, universal ABI
./gradlew :app:assembleMobileUniversalRelease

# Build a specific flavor/ABI (e.g. TV)
./gradlew :app:assembleTvUniversalDebug
```

Outputs land in `app/build/outputs/apk/`.

**Tip:** use `--console=plain` and `--warning-mode summary` in CI-friendly output; disable the daemon with `--no-daemon` if you hit memory issues.

---

## Building with GitHub Actions

You don't need a local Android SDK if you prefer to use CI:

1. Fork the repository (or push a branch to your fork).
2. Open **Actions** → select the workflow you need:
   - **Build APKs** — triggered on push to `main`/`dev` or manually (`workflow_dispatch`). Runs unit tests, then builds, signs, and uploads all release variants.
   - **Pull Request Checks** — runs automatically on every PR: compiles `assembleMobileUniversalDebug`, runs lint and unit tests.
   - **Release** — triggered when `versionName` changes on `main` (or manually). Verifies reproducibility, builds all six signed variants, and creates a GitHub Release with a changelog.
3. Download artifacts from the run summary.

Signing secrets (see below) are only needed for the **Build APKs** and **Release** workflows if you want signed APKs.

| Secret | Purpose |
|---|---|
| `KEYSTORE` | Base64-encoded signing keystore |
| `KEY_ALIAS` | Keystore alias |
| `KEYSTORE_PASSWORD` | Keystore password |
| `KEY_PASSWORD` | Key password |
| `LASTFM_API_KEY`, `LASTFM_SECRET` | Optional — Last.fm scrobbling |
| `TOGETHER_BEARER_TOKEN` | Optional — Together online hosting |
| `CANVAS_BEARER_TOKEN` | Optional — canvas artwork service |

---

## Running tests

```bash
./gradlew :app:test
```

Unit tests live in `app/src/test/kotlin/` (e.g. `StreamChunkResolverTest`). Library modules (`innertube`, `shazamkit`, …) ship their own JUnit tests as well; they run under the root `test` task.

---

## Conventional commits

We use [Conventional Commits](https://www.conventionalcommits.org/) — the Release workflow's changelog is generated from these prefixes:

| Prefix | Changelog section |
|---|---|
| `feat:` | Features |
| `fix:` | Fixes |
| `perf:` | Performance |
| `refactor:` | Refactors |
| `docs:` | Documentation |
| `build:` / `ci:` | Build / CI |
| `test:` / `style:` / `chore:` / `revert:` | Other changes |

Example:

```
feat(player): add gapless playback toggle
fix(lyrics): handle missing timestamp lines
docs(readme): clarify nightly build channel
```

---

## Pull request workflow

1. Create a branch from `dev` (or `main` for trivial docs changes): `git checkout -b feat/my-change`.
2. Make your changes. Keep them focused — one logical change per PR.
3. Run the local build + tests described above before pushing.
4. Push and open a PR. CI runs **Pull Request Checks** automatically.
5. A maintainer will review; address feedback and keep the PR up to date with `dev`.

Guidelines:

- Explain **why** in the PR description, not just what.
- Don't include unrelated reformatting or renamed identifiers in the same PR.
- For UI changes, attach screenshots when possible.
- If you touch sensitive behavior (auth, playback, downloads), mention what you tested.

---

## License headers

Every source file begins with the SpireBeat license header:

```kotlin
/*
 * SpireBeat (2026)
 * © Aayush Pokhrel — github.com/aayushpokhrel49/spirebeat
 * GPL-3.0 License | Contributors: see git history
 * Do not remove or alter this notice. - Per GPL-3.0 Section 4 & Section 5
 */
```

The `Kotlin Header` workflow (manual dispatch) can re-apply headers across the codebase automatically.

---

## Reporting issues

- Use the [issue templates](https://github.com/aayushpokhrel49/spirebeat/issues/new/choose) — bug reports and feature requests each have a form.
- Include your build variant (mobile/tv, ABI), Android version, and steps to reproduce.
- For crashes, attach the report shown in the built-in debug activity (crash reporter).

---

## Community

SpireBeat is an independent project maintained by Aayush Pokhrel. Questions and discussion happen in the GitHub Issues / Discussions of this repository.

<sub>SpireBeat: Engineering audio freedom.</sub>
