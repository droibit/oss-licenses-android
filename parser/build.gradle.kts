plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin)
}

android {
  namespace = "com.github.droibit.oss_licenses.parser"
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    minSdk = 21
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
    }
  }
}

dependencies {
  api(platform(libs.kotlin.coroutines.bom))
  api(libs.kotlin.coroutines.core)

  implementation(libs.androidx.annotation)
  implementation(libs.androidx.core)
  implementation(libs.okio)

  testImplementation(libs.junit)
  testImplementation(libs.truth)
  testImplementation(libs.mockk)
  testImplementation(libs.kotlin.coroutines.test)
}

apply(from = "$rootDir/gradle/gradle-mvn-push.gradle.kts")
