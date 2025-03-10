plugins {
  alias(libs.plugins.android.application) version "7.4.0"
  alias(libs.plugins.licensee)
  alias(libs.plugins.licensee.bridge) version "dev"
}

android {
  namespace = "com.example.integration_test.android.application.min_supported_version"
  compileSdk = 33

  defaultConfig.minSdk = 21

  lint.checkReleaseBuilds = false
}

dependencies {
  implementation("com.example:example:1.0.0")
}

licensee {
  allow("Apache-2.0")
}
