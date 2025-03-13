plugins {
  alias(libs.plugins.osslicenses.android.application)
  alias(libs.plugins.osslicenses.android.wear.compose)
  alias(libs.plugins.osslicenses)
}

android {
  namespace = "com.github.droibit.oss_licenses.sample"

  defaultConfig {
    applicationId = "com.github.droibit.oss_licenses.sample"
    targetSdk = 35
    minSdk = 26
  }
}

dependencies {
  implementation(projects.uiWear)
  implementation(projects.uiWearComposeMaterial)
  implementation(projects.uiWearComposeMaterial3)

  implementation(libs.androidx.activity.compose)
  implementation(libs.bundles.androidx.wear.compose.material)
  implementation(libs.bundles.androidx.wear.compose.material3)
  implementation(libs.horologist.compose.layout)
  implementation(libs.horologist.compose.material)

  implementation(libs.androidx.fragment)
  implementation(libs.androidx.lifecycle.runtime)
  implementation(libs.androidx.recyclerView)
  implementation(libs.androidx.wear)

  implementation(libs.play.services.wearable)

  implementation(libs.dagger)
}
