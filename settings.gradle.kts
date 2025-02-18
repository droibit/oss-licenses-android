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

  // https://github.com/google/play-services-plugins/issues/223
  resolutionStrategy {
    eachPlugin {
      if (requested.id.id == "com.google.android.gms.oss-licenses-plugin") {
        useModule("com.google.android.gms:oss-licenses-plugin:${requested.version}")
      }
    }
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

include(
  ":parser",
  ":sample-mobile",
  ":sample-wear",
  ":ui-compose-material3",
  ":ui-compose-screenshots",
  ":ui-viewmodel",
  ":ui-wear",
  ":ui-wear-compose-core",
  ":ui-wear-compose-material",
  ":ui-wear-compose-material3",
)
