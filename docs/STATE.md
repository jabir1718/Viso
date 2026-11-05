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
- compileSdk 36 — CI installs Android SDK Platform 36; actual compile not yet reached.
- Gradle Wrapper 8.11.1 — COMMITTED by GitHub Actions at commit f402cb5227a0af38d3e7752803fa9a58f370dbba; gradlew, gradlew.bat, wrapper properties and wrapper JAR verified on branch.

## CI
- .github/workflows/build.yml — committed.
- Wrapper job — SUCCESS: generated and committed wrapper.
- First assemble-debug attempt — FAILED before Gradle build. android-actions/setup-android@v3 attempted to install obsolete package "tools"; sdkmanager reported "Failed to find package 'tools'".
- Workflow corrected: setup-android now requests platform-tools only; sdkmanager explicitly installs platforms;android-36. Pinned build-tools;36.0.0 removed.
- Retry result — PENDING. No APK artifact and no successful assembleDebug yet.

## Agla kaam
- Inspect the next Actions run. If SDK setup succeeds, address only actual Gradle/build errors from logs. M1 stays pending until assembleDebug is green and APK artifact is verified.
