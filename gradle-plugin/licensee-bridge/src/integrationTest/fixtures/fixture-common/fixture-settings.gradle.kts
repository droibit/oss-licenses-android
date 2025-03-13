pluginManagement {
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

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    maven(url = "file://${settingsDir.absolutePath}/../fixture-common/repo") {
      content {
        includeGroup("com.example")
      }
    }
    google {
      content {
        includeGroupByRegex("com\\.android.*")
        includeGroupByRegex("com\\.google.*")
        includeGroupByRegex("androidx.*")
      }
    }
    mavenCentral()
  }

  versionCatalogs {
    create("libs") {
      from(files("../../../../../../gradle/libs.versions.toml"))
    }
  }
}

includeBuild("../../../../../") {
  dependencySubstitution {
    substitute(
      module("io.github.droibit.oss-licenses-android.licensee-bridge:io.github.droibit.oss-licenses-android.licensee-bridge.gradle.plugin"),
    ).using(project(":licensee-bridge"))
  }
}
