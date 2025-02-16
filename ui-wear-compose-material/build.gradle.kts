@file:Suppress("UnstableApiUsage")

plugins {
  alias(libs.plugins.osslicenses.android.library)
  alias(libs.plugins.osslicenses.android.wear.compose)
  alias(libs.plugins.osslicenses.android.maven.publish)
  alias(libs.plugins.roborazzi)
}

android.namespace = "com.github.droibit.oss_licenses.ui.wear.compose.material"

dependencies {
  implementation(projects.uiWearComposeCore)
  implementation(projects.uiViewmodel)

  api(libs.androidx.activity)
  implementation(libs.androidx.activity.compose)
  api(libs.bundles.androidx.wear.compose.material)
  implementation(libs.bundles.androidx.wear.compose.navigation)
  implementation(libs.bundles.androidx.lifecycle.viewModel.compose)

  testImplementation(libs.junit)
  testImplementation(libs.robolectric)
  testImplementation(projects.uiComposeScreenshots)
}
