plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin)
  alias(libs.plugins.compose.compiler)
}

android {
  namespace = "com.github.droibit.oss_licenses.ui.wear.compose.material"
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    minSdk = 25

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildFeatures {
    compose = true
  }

  packaging {
    resources {
      excludes += listOf(
        "/META-INF/{AL2.0,LGPL2.1}",
        "/META-INF/core_debug.kotlin_module",
      )
    }
  }
}

dependencies {
  api(projects.uiWearComposeCore)
  api(projects.uiViewmodel)
  api(projects.uiComposeCore)

  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.bundles.androidx.wear.compose.material)
  implementation(libs.bundles.androidx.wear.compose.navigation)
  implementation(libs.bundles.androidx.lifecycle.viewModel.compose)

  debugImplementation(libs.androidx.compose.ui.tooling)
  debugImplementation(libs.androidx.compose.ui.tooling.preview)
  debugImplementation(libs.androidx.wear.compose.ui.tooling)
}

apply(from = "$rootDir/gradle/gradle-mvn-push.gradle.kts")
