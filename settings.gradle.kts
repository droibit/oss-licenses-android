@file:Suppress("UnstableApiUsage")

pluginManagement {
  includeBuild("build-logic")
  repositories {
    google {
      content {
        includeGroupByRegex("com\\.android.*")
        includeGroupByRegex("com\\.google.*")
        includeGroupByRegex("androidx.*")
      }
    }
    mavenCentral()
    gradlePluginPortal()
  }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google {
      content {
        includeGroupByRegex("com\\.android.*")
        includeGroupByRegex("com\\.google.*")
        includeGroupByRegex("androidx.*")
      }
    }
    mavenCentral()
  }
}

rootProject.name = "oss-licenses-android"

include(
  ":parser",
  ":sample-mobile",
  ":sample-mobile-licensee-bridge",
  ":sample-wear",
  ":ui-compose-material3",
  ":ui-compose-screenshots",
  ":ui-viewmodel",
  ":ui-wear",
  ":ui-wear-compose-core",
  ":ui-wear-compose-material",
  ":ui-wear-compose-material3",
)
includeBuild("gradle-plugin") {
  dependencySubstitution {
    substitute(module("io.github.droibit.oss-licenses-android.licensee-bridge:io.github.droibit.oss-licenses-android.licensee-bridge.gradle.plugin"))
      .using(project(":licensee-bridge"))
  }
}
