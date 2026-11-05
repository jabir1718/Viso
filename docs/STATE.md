# Viso — State

Branch: m1-skeleton | Milestone: M1 | Build: pending
Repo: github.com/jabir1718/Viso

## Accepted (rewrite nahi karna)
- app, core, ui, media, audio, shader, lyrics, copyright, export modules
- gradle/libs.versions.toml, root+app+core build.gradle.kts, manifest, MainActivity, resources
- core/data, core/di, nav scaffold

## Rules
- Ek waqt main ek file. Full path header. 300 line max.
- Naya file ya diff only. Accepted file rewrite nahi.
- Pakka na ho to VERIFY: likho, invent na karo.
- Ek commit per file, message "MN: ...". Force-push nahi.
- Har milestone alag branch. Green build ke baghair agla milestone nahi.

## VERIFY (khula)
- core/build.gradle.kts: Room+KSP+Hilt plugins — CONFIRMED
- compileSdk 36 vs AGP 8.9.2 — VERIFY: Android SDK Platform 36 installation has not been confirmed; CI workflow will install it on the runner.
- Gradle Wrapper — NOT COMMITTED: Termux wrapper task has not produced files. CI temporarily generates Gradle 8.11.1 wrapper files on the runner before assembleDebug; this does not add wrapper files to the repository.

## CI
- .github/workflows/build.yml — committed; runs on push to m1-skeleton or manual dispatch.
- Runner: Ubuntu, Temurin JDK 17, Gradle 8.11.1, Android SDK Platform 36 / Build Tools 36.0.0.
- CI build result — PENDING; do not mark M1 green until an actual successful run and APK artifact are verified.

## Agla kaam
- M1: inspect GitHub Actions run, fix only evidenced build errors, obtain debug APK artifact, then commit generated Wrapper files to the repository.
