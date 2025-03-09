plugins {
  alias(libs.plugins.osslicenses.android.application)
  alias(libs.plugins.osslicenses.android.compose)
  alias(libs.plugins.osslicenses)
}

android {
  namespace = "com.github.droibit.oss_licenses.sample"

  defaultConfig {
    applicationId = "com.github.droibit.oss_licenses.sample"
    targetSdk = 35
    minSdk = 24
  }
}

dependencies {
  implementation(projects.uiComposeMaterial3)

  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.navigation.compose)

  implementation(libs.play.services.wearable)
  implementation(libs.dagger)
}
