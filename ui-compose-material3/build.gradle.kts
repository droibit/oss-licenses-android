plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin)
  alias(libs.plugins.compose.compiler)
}

android {
  namespace = "com.github.droibit.oss_licenses.ui.compose.material3"
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    minSdk = 24

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildFeatures {
    compose = true
  }


  packaging {
    resources {
      excludes += listOf(
        "/META-INF/{AL2.0,LGPL2.1}",
        "/META-INF/core_debug.kotlin_module"
      )
    }
  }
}

dependencies {
  implementation(projects.uiViewmodel)
  implementation(projects.uiComposeCore)

  api(libs.androidx.activity)
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.navigation.compose)
  implementation(libs.bundles.androidx.lifecycle.viewModel.compose)

  debugImplementation(libs.androidx.compose.ui.tooling)
  debugImplementation(libs.androidx.compose.ui.tooling.preview)
}

apply(from = "$rootDir/gradle/gradle-mvn-push.gradle.kts")
