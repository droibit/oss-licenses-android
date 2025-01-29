pluginManagement {
  repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
  }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
  }
}

include(
  ":parser",
  ":sample-mobile",
  ":sample-wear",
  ":ui-compose-material3",
  ":ui-viewmodel",
  ":ui-wear",
  ":ui-wear-compose-core",
  ":ui-wear-compose-material",
  ":ui-wear-compose-material3",
)
