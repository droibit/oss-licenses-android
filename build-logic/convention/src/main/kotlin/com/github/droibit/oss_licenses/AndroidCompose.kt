package com.github.droibit.oss_licenses

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("UnstableApiUsage")
internal fun Project.configureAndroidCompose(
  commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
  commonExtension.apply {
    buildFeatures {
      compose = true
    }

    testOptions {
      unitTests {
        isIncludeAndroidResources = true
      }
    }
  }

  dependencies {
    "implementation"(platform(libs.findLibrary("androidx-compose-bom").get()))
    "debugImplementation"(libs.findLibrary("androidx-compose-ui-tooling").get())
    "debugImplementation"(libs.findLibrary("androidx-compose-ui-tooling-preview").get())
    "debugImplementation"(libs.findLibrary("androidx-ui-test-manifest").get())
  }
}

internal fun Project.configureAndroidWearCompose(
  commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
  configureAndroidCompose(commonExtension)

  dependencies {
    "debugImplementation"(libs.findLibrary("androidx-wear-tooling-preview").get())
    "debugImplementation"(libs.findLibrary("androidx-wear-compose-ui-tooling").get())
  }
}
