@file:Suppress("UnstableApiUsage")

plugins {
  alias(libs.plugins.osslicenses.android.library)
  alias(libs.plugins.osslicenses.android.compose)
  alias(libs.plugins.osslicenses.android.maven.publish)
  alias(libs.plugins.roborazzi)
}

android.namespace = "com.github.droibit.oss_licenses.ui.compose.material3"

dependencies {
  implementation(projects.uiViewmodel)

  api(libs.androidx.activity)
  implementation(libs.androidx.activity.compose)

  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.material3.adaptive.layout)
  implementation(libs.androidx.compose.material3.adaptive.navigation)
  implementation(libs.bundles.androidx.lifecycle.viewModel.compose)

  testImplementation(libs.junit)
  testImplementation(libs.robolectric)
  testImplementation(projects.uiComposeScreenshots)
}
