plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin)
  alias(libs.plugins.compose.compiler)
}

android {
  namespace = "com.github.droibit.oss_licenses.ui.wear.compose.core"
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    minSdk = 25

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildFeatures {
    compose = true
  }
}

dependencies {
  implementation(projects.parser)
  implementation(projects.uiComposeCore)

  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.wear.compose.foundation)
}

apply(from = "$rootDir/gradle/gradle-mvn-push.gradle.kts")
