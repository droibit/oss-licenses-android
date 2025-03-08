plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.licensee)
  alias(libs.plugins.licensee.bridge) version "dev"
}

android {
  namespace = "com.example.integration_test.android.application.product_flavors"
  compileSdk = 35

  defaultConfig.minSdk = 21

  lint.checkReleaseBuilds = false

  flavorDimensions += "version"
  productFlavors {
    create("demo") {
      dimension = "version"
    }
    create("full") {
      dimension = "version"
    }
  }
}

dependencies {
  implementation("com.example:example:1.0.0")
}

licensee {
  allow("Apache-2.0")
}
