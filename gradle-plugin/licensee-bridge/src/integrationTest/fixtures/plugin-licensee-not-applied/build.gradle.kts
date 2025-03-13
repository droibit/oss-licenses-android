plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.licensee.bridge) version "dev"
}

android {
  namespace = "com.example.integration_test.licensee.not_applied"
  compileSdk = 35

  defaultConfig.minSdk = 21

  lint.checkReleaseBuilds = false
}

dependencies {
  implementation("com.example:example:1.0.0")
}
