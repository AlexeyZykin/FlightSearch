
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.navigation.safeargs) apply false
    alias(libs.plugins.kapt)
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.secrets) apply false
    alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
}