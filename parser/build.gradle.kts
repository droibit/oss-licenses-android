plugins {
  id("com.android.library")
  id("kotlin-android")
}

android {
  namespace = "com.github.droibit.oss_licenses.parser"
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    minSdk = 19
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
    }
  }
}

dependencies {
  implementation(libs.androidx.annotation)

  api(libs.kotlin.coroutines.core)
  api(libs.okio)

  testImplementation(libs.junit)
}

apply(from = "$rootDir/gradle/gradle-mvn-push.gradle.kts")
