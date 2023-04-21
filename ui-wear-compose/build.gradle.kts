plugins {
  id("com.android.library")
  id("kotlin-android")
}

android {
  namespace = "com.github.droibit.oss_licenses.ui.wear.compose"
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    minSdk = 25

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildFeatures {
    compose = true
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

 packagingOptions {
   resources {
     excludes += listOf(
       "/META-INF/{AL2.0,LGPL2.1}",
       "/META-INF/core_debug.kotlin_module"
     )
   }
 }
}

dependencies {
  api(project(":ui-viewmodel"))

  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.navigation.compose)
  implementation(libs.bundles.androidx.wear.compose)
  implementation(libs.androidx.wear.compose.navigation)
  implementation(libs.androidx.compose.ui.tooling)
  debugImplementation(libs.androidx.compose.ui.tooling.preview)
}

apply(from = "$rootDir/gradle/gradle-mvn-push.gradle.kts")
