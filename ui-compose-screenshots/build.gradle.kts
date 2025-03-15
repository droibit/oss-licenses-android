plugins {
  alias(libs.plugins.osslicenses.android.library)
  alias(libs.plugins.compose.compiler)
}

android {
  namespace = "com.github.droibit.oss_licenses.ui.wear.compose.screenshots"

  defaultConfig {
    minSdk = 24
  }

  buildFeatures {
    compose = true
  }
}

dependencies {
  implementation(projects.uiViewmodel)

  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.compose.foundation)

  implementation(libs.androidx.ui.test.junit4)
  implementation(libs.junit)
  implementation(libs.robolectric)
  implementation(libs.roborazzi)
  implementation(libs.roborazzi.compose)
  implementation(libs.roborazzi.rule)
}
