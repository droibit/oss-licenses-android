plugins {
  id("com.android.library")
  id("kotlin-android")
}

android {
  namespace = "com.github.droibit.oss_licenses.ui.wear"
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    minSdk = 24
  }

  packaging {
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

  implementation(libs.androidx.annotation)
  implementation(libs.androidx.fragment)
  implementation(libs.androidx.lifecycle.runtime)
  implementation(libs.androidx.lifecycle.viewModel)
  implementation(libs.androidx.recyclerView)
  implementation(libs.androidx.wear)
}

apply(from = "$rootDir/gradle/gradle-mvn-push.gradle.kts")
