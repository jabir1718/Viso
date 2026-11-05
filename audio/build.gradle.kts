plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}
android { namespace = "com.viso.audio"; compileSdk = 36; defaultConfig { minSdk = 26 } }
dependencies { implementation(project(":core")); implementation(libs.androidx.core.ktx) }
