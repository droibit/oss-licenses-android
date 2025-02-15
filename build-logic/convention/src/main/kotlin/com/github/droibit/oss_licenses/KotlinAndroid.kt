package com.github.droibit.oss_licenses

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

internal fun Project.configureKotlinAndroid(
  commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
  commonExtension.apply {
    compileSdk = 35

    compileOptions {
      sourceCompatibility = JavaVersion.VERSION_17
      targetCompatibility = JavaVersion.VERSION_17
    }
  }
  configureKotlin(isLibrary = commonExtension is LibraryExtension)
}

internal fun Project.configureKotlin(isLibrary: Boolean) {
  configure<KotlinAndroidProjectExtension> {
    compilerOptions {
      jvmTarget = JvmTarget.JVM_17

      // Align with Jetpack Compose's Kotlin compatibility requirements.
      if (isLibrary) {
        apiVersion = KotlinVersion.KOTLIN_1_9
        languageVersion = KotlinVersion.KOTLIN_1_9
      }
    }
  }
}
