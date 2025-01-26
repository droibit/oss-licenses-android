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

  api(platform(libs.kotlin.coroutines.bom))
  api(libs.kotlin.coroutines.core)
  api(libs.androidx.lifecycle.viewModel)

  implementation(libs.androidx.annotation)
}

apply(from = "$rootDir/gradle/gradle-mvn-push.gradle.kts")
