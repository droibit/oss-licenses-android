plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin)
  alias(libs.plugins.compose.compiler)
  id("com.google.android.gms.oss-licenses-plugin")
}

android {
  compileSdk = libs.versions.compileSdk.get().toInt()
  namespace = "com.github.droibit.oss_licenses.sample"

  defaultConfig {
    applicationId = "com.github.droibit.oss_licenses.sample"
    minSdk = 26
    targetSdk = libs.versions.targetSdk.get().toInt()
    versionCode = 1
    versionName = "1.0.0"
  }

  buildTypes {
    debug {
      isDebuggable = true
      isMinifyEnabled = false
      isShrinkResources = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
    release {
      isMinifyEnabled = true
      isShrinkResources = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

      // Allow testing the release build type with `./gradlew sample-wear:installRelease`
      signingConfig = signingConfigs.getByName("debug")
    }
  }

  buildFeatures {
    compose = true
    buildConfig = true
  }
}

dependencies {
  implementation(projects.uiWear)
  implementation(projects.uiWearComposeMaterial)
  implementation(projects.uiWearComposeMaterial3)

  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.bundles.androidx.wear.compose.material)
  implementation(libs.bundles.androidx.wear.compose.material3)
  implementation(libs.horologist.compose.layout)
  implementation(libs.horologist.compose.material)

  implementation(libs.androidx.fragment)
  implementation(libs.androidx.lifecycle.runtime)
  implementation(libs.androidx.recyclerView)
  implementation(libs.androidx.wear)

  implementation(libs.playServices.wearable)

  implementation(libs.dagger)
}
