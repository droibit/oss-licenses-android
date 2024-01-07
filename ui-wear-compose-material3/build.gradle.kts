@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin)
}

android {
  namespace = "com.github.droibit.oss_licenses.ui.wear.compose.material3"
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    minSdk = 25

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildFeatures {
    compose = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
  }

  kotlinOptions {
    freeCompilerArgs = listOf(
      *freeCompilerArgs.toTypedArray(),
      // "-opt-in=androidx.wear.compose.material.ExperimentalWearMaterialApi",
      // "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
    )
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
  api(projects.uiViewmodel)

  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.bundles.androidx.wear.compose.material3)
  implementation(libs.bundles.androidx.wear.compose.navigation) {
    exclude(group = "androidx.wear.compose", module = "compose-material")
  }

  debugImplementation(libs.androidx.compose.ui.tooling)
  debugImplementation(libs.androidx.compose.ui.tooling.preview)
  debugImplementation(libs.androidx.wear.compose.ui.tooling)
}

apply(from = "$rootDir/gradle/gradle-mvn-push.gradle.kts")
