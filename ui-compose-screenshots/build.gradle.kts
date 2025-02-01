plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin)
  alias(libs.plugins.compose.compiler)
}

android {
  namespace = "com.github.droibit.oss_licenses.ui.wear.compose.screenshots"
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    minSdk = 25
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
  implementation(projects.parser)

  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.compose.foundation)

  implementation(libs.androidx.ui.test.junit4)
  implementation(libs.junit)
  implementation(libs.robolectric)
  implementation(libs.roborazzi)
  implementation(libs.roborazzi.compose)
  implementation(libs.roborazzi.rule)
}
