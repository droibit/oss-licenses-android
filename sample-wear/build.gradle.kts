plugins {
  id("com.android.application")
  id("kotlin-android")
  id("com.google.android.gms.oss-licenses-plugin")
}

android {
  compileSdk = libs.versions.compileSdk.get().toInt()
  namespace = "com.github.droibit.oss_licenses.sample"

  defaultConfig {
    applicationId = "com.github.droibit.oss_licenses.sample"
    minSdk = 25
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
    }
  }

  buildFeatures {
    compose = true
    buildConfig = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
  }

  kotlinOptions {
    freeCompilerArgs = listOf(
      *freeCompilerArgs.toTypedArray(),
      "-opt-in=androidx.wear.compose.material.ExperimentalWearMaterialApi",
      "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
    )
  }
}

dependencies {
  implementation(projects.uiWear)
  implementation(projects.uiWearCompose)

  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.bundles.androidx.wear.compose)
  implementation(libs.horologist.compose.layout)
  debugImplementation(libs.androidx.compose.ui.tooling)

  implementation(libs.androidx.fragment)
  implementation(libs.androidx.lifecycle.runtime)
  implementation(libs.androidx.recyclerView)
  implementation(libs.androidx.wear)

  implementation(libs.playServices.wearable)

  implementation(libs.dagger)
}