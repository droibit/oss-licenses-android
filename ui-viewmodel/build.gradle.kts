plugins {
  id("com.android.library")
  id("kotlin-android")
}

android {
  namespace = "com.github.droibit.oss_licenses.ui.viewmodel"
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    minSdk = 24

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
}

dependencies {
  api(project(":parser"))

  implementation(libs.kotlin.coroutines.core)
  implementation(libs.bundles.androidx.lifecycle.viewModel)
}

apply(from = "$rootDir/gradle/gradle-mvn-push.gradle.kts")
