plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.licensee) version "1.11.0"
  alias(libs.plugins.licensee.bridge) version "dev"
}

android {
  namespace = "com.example.integration_test.licensee.min_supported_version"
  compileSdk = 35

  defaultConfig.minSdk = 21

  lint.checkReleaseBuilds = false
}

dependencies {
  implementation("com.example:example:1.0.0")
}

licensee {
  allow("Apache-2.0")
}
