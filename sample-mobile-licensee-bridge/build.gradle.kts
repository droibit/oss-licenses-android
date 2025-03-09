plugins {
  alias(libs.plugins.osslicenses.android.application)
  alias(libs.plugins.osslicenses.android.compose)
  alias(libs.plugins.licensee)
  alias(libs.plugins.licensee.bridge)
}

android {
  namespace = "com.github.droibit.oss_licenses.licensee_bridge.sample"

  defaultConfig {
    applicationId = "com.github.droibit.oss_licenses.licensee_bridge.sample"
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
  implementation(libs.play.services.osslicenses)
  implementation(libs.dagger)
}

licensee {
  allow("Apache-2.0")
  allowUrl("https://developer.android.com/studio/terms.html")
}

// Force consistent versions of vectordrawable libraries to avoid the namespace conflict
// between androidx.vectordrawable:vectordrawable and vectordrawable-animated
// This resolves the "Namespace 'androidx.vectordrawable' is used in multiple modules" error
configurations.all {
  resolutionStrategy {
    force("androidx.vectordrawable:vectordrawable:1.2.0")
    force("androidx.vectordrawable:vectordrawable-animated:1.2.0")
  }
}
