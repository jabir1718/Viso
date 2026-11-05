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
- compileSdk 36 vs AGP 8.9.2 — VERIFY: Android SDK Platform 36 installation has not been confirmed; check on the build machine

## Agla kaam
- M1: build green
