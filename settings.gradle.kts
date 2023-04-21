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
  ":ui-compose",
  ":ui-viewmodel",
  ":ui-wear",
  ":ui-wear-compose",
)
