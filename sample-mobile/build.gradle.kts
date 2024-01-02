plugins {
  id("com.android.application")
  id("kotlin-android")
  id("com.google.android.gms.oss-licenses-plugin")
}

android {
  namespace = "com.github.droibit.oss_licenses.sample"
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    applicationId = "com.github.droibit.oss_licenses.sample"
    minSdk = 24
    targetSdk = libs.versions.targetSdk.get().toInt()
    versionCode = 1
    versionName = "1.0.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
      "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
    )
  }
}

dependencies {
  implementation(projects.uiCompose)

  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.bundles.androidx.compose.material3)
  implementation(libs.androidx.navigation.compose)

  debugImplementation(libs.androidx.compose.ui.tooling)

  implementation(libs.dagger)
}
