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
  api(projects.parser)
  api(libs.androidx.wear.compose.foundation)
  api(libs.androidx.navigation.runtime)

  implementation(libs.androidx.annotation)

  testImplementation(libs.junit)
  testImplementation(libs.androidx.test.junit)
  testImplementation(libs.truth)
  testImplementation(libs.robolectric)
}

apply(from = "$rootDir/gradle/gradle-mvn-push.gradle.kts")
