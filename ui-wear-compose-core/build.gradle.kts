plugins {
  alias(libs.plugins.osslicenses.android.library)
  alias(libs.plugins.osslicenses.android.wear.compose)
  alias(libs.plugins.osslicenses.android.maven.publish)
}

android.namespace = "com.github.droibit.oss_licenses.ui.wear.compose.core"

dependencies {
  api(projects.parser)
  api(libs.androidx.wear.compose.foundation)
  api(libs.androidx.navigation.runtime)

  implementation(libs.androidx.annotation)

  testImplementation(libs.junit)
  testImplementation(libs.androidx.test.junit)
  testImplementation(libs.truth)
  testImplementation(libs.robolectric)
}
