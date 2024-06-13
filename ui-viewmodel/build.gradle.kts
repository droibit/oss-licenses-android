plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin)
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
  api(projects.parser)

  implementation(libs.kotlin.coroutines.core)
  implementation(libs.bundles.androidx.lifecycle.viewModel)
}

apply(from = "$rootDir/gradle/gradle-mvn-push.gradle.kts")
