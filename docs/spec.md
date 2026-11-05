# Viso — Project Specification

## Product
Viso is an offline, login-free, watermark-free Android video and audio editor for short-form creators. The first priority is a working APK with a reliable minimal export path. The standout feature is a professional GPU audio-reactive visualizer.

### Product priorities
1. Professional GPU audio-reactive visualizer.
2. Deep audio engine and editing controls.
3. Copyright-risk awareness and asset-license tracking.
4. Remaining video, lyric, compositing, export and publishing features.

## Technical architecture
- Kotlin, Jetpack Compose, Material 3, MVVM, Hilt, Room, WorkManager and DataStore.
- minSdk 26; target/compile SDK as established by the build configuration.
- OpenGL ES 3.0 and GLSL ES 3.00 for GPU visualizer and frame compositing.
- MediaCodec for frame encoding/compositing.
- FFmpeg is limited to demuxing, muxing, audio filters and containers; do not use FFmpeg for video-frame compositing.
- Candidate audio/media technologies: Oboe, TarsosDSP, Rubber Band, Essentia, RNNoise, whisper.cpp, demucs.cpp downloadable model, MediaPipe, a maintained community FFmpegKit fork (not archived arthenica), youtubedl-android and android-gpuimage. Integrate only in the milestone where required and verify current Android compatibility before adding.
- Keep dependencies one-way: ui -> feature modules -> core. Feature modules must not depend on each other internally.
- Hide implementation behind use cases and public interfaces. Avoid God classes and static mutable state.

## Modules
- :app
- :core
- :media
- :audio
- :shader
- :lyrics
- :copyright
- :export
- :ui

## Data model / Room v1
Entities:
- project
- track
- clip
- effect
- keyframe
- lyric_line
- asset_license
- preset
- render_job

Rules:
- Store timeline time in frame units.
- Represent destructive transforms as keyframe rows; do not bake them into destructive buffers.
- Every imported asset must have a mandatory asset_license record.
- Keep Room schema export configured and versioned.

## UI / editor layout
- Bottom navigation: Editor, Audio, Visualizer, Lyrics, Copyright, Export.
- Preview above the timeline.
- Right-side vertical rail with toggleable floating glass panels.
- Timeline track capacity: 4 video, 6 audio, 3 overlay, 1 lyric and 1 text track.
- Track controls: lock, hide, mute, solo.
- Include safe-zone guides, grid, snap indicator and haptic drag feedback.
- Keep editing controls discoverable and grouped; avoid cluttering the main screen with unrelated buttons.

## Milestone roadmap

### P0 — M1 skeleton / build green
- Complete Android/Gradle skeleton and verify a real debug build.
- No feature development before the build is green.
- Current branch: m1-skeleton.
- Current build state: pending until a successful local build is reported.

### P1 — M2 timeline + minimal export (first usable APK)
- Timeline editing: trim, split, ripple delete, snap, reorder and track controls.
- Undo/redo and autosave.
- Proxy support and frame stepping.
- Minimal export must produce a real video file that can be opened outside the app.
- This is the first APK milestone.

### P2 — M3 audio core (internal test)
- Oboe-based audio foundation.
- Per-clip volume, pitch and tempo.
- Fades and automation.
- EQ, compressor and limiter.
- Loudness/LUFS measurement and normalization.
- Noise reduction, de-esser and audio analysis/BPM where compatible.
- Rubber Band time/pitch processing; verify APIs and licenses before integration.

### P3 — M4 visualizer (main differentiator)
- OpenGL ES 3.0 / GLSL ES 3.00 pipeline.
- FFT size 4096, six frequency bands, attack/decay/release smoothing, beat detection and stereo routing.
- Automation/LFO, multiple visual layers and effects.
- Image-reactive assets, gradients, palette/LUT controls and post effects.
- GPU compositing and performance quality scaling.
- Target 40 presets.
- M4 special workflow: one shader file per reply; do not batch shader files or enable autopilot for shader authoring.

### P4 — M5 lyrics
- whisper.cpp transcription, word/line timestamps and sync.
- Karaoke highlighting.
- Translation and romanization support.
- SRT/VTT import and export.

### P5 — M7 Copyright Shield
- Risk score and explainable warning profile.
- Suggested transformation chain and platform-specific warning profiles.
- Asset license ledger.
- Optional downloadable demucs model.
- Never claim pitch or speed changes alone defeat audio fingerprinting or guarantee copyright clearance.

### P6 — M6 color/tracking + M9 hardening
- Color grading, masks, transforms, picture-in-picture and curves/HSL/scopes/LUT.
- MediaPipe tracking and stabilization where feasible.
- Permission safety, storage cleaner, crash-safe render resume, low-end device profile, thermal handling, ProGuard/R8, baseline profile and test matrix.

### Final — M8 full import/export/publish
- Complete import/export workflows, presets and resumable rendering.
- Transparent WebM and PNG sequence where supported.
- Link import and direct upload are deferred until the end; the earlier minimal local-file export is sufficient for the first APK.

## Milestone acceptance gates
- Each milestone has its own branch (m1-skeleton, m2-timeline, etc.).
- Do not start the next milestone until the current milestone's build is green and the user confirms the gate.
- Tag each verified green build: m1-green, m2-green, etc.
- Merge to main only after a green build.
- Never force-push.
- Never claim a build or launch succeeded without actual build output/evidence.
- If a build fails, fix only the broken file(s), one file per commit, and rebuild before adding features.

## File and coding rules
- One file at a time; put its full repository path in the response header.
- Maximum 300 lines per file response.
- Add new files or provide focused diffs only. Never rewrite accepted files without explicit need.
- Do not add placeholders, TODO-only implementations or incomplete core functions.
- If an API, flag, coordinate, dependency version or behavior is uncertain, mark it VERIFY and list it; do not invent certainty.
- One commit per file. Commit message prefix: "MN: ...", using the active milestone number.
- Code comments must be in English.
- Do not add Android permissions without asking first.
- Keep accepted project files stable; no unrelated refactors or regressions.

## Collaboration workflow
- For a milestone, work through the complete scoped batch without repeatedly asking for permission inside that milestone when autopilot is authorized.
- Perform three passes: write files, self-audit imports/dependencies/namespaces/Room/Hilt/API compatibility/file length, then present only corrected final files.
- Stop at the milestone boundary, a user-only decision, or an output limit.
- M1 remains gated: generate and commit the Gradle wrapper, run the debug build, fix actual errors, and stop when M1 is green. Do not proceed to M1 Part B/M2 until the successful build and user confirmation.
- User runs local Android Studio/Windows terminal commands; do not pretend ChatGPT can execute commands on the user's device.

## Current M1 build procedure
The Gradle wrapper files were missing from the m1-skeleton branch at the last repository inspection:
- gradlew
- gradlew.bat
- gradle/wrapper/gradle-wrapper.jar
- gradle/wrapper/gradle-wrapper.properties

Generate locally with a compatible Gradle installation:
`gradle wrapper --gradle-version 8.11.1`

Commit wrapper files:
`git add gradlew gradlew.bat gradle/wrapper`
`git commit -m "M1: add Gradle wrapper 8.11.1"`

Then run:
- PowerShell: ` .\gradlew.bat assembleDebug --stacktrace `
- macOS/Linux/Git Bash: `./gradlew assembleDebug --stacktrace`

Use JDK 17 as the conservative baseline (JDK 21 may be compatible; verify if errors indicate otherwise). VERIFY: Android SDK Platform 36 installation has not been confirmed; check the SDK Manager on the build machine before assuming it is installed. Do not commit local.properties. If SDK location is missing, set the actual local sdk.dir in local.properties on the developer machine.

## M1 verified repository facts at last inspection
- Branch: m1-skeleton.
- Android SDK Platform 36 installation: VERIFY; not confirmed yet.
- core/build.gradle.kts includes Android library, Kotlin Android, KSP and Hilt plugins; Room runtime/ktx/compiler and Hilt Android/compiler dependencies; Room schemaLocation configured.
- .gitignore ignores local.properties and build outputs; it does not ignore Gradle wrapper files.
- Gradle wrapper files listed above were absent.
- No successful assembleDebug output has been provided yet. Build status remains pending.
