plugins {
  alias(libs.plugins.spotless)
}

subprojects {
  apply(plugin = "com.diffplug.spotless")

  spotless {
    kotlin {
      target("**/*.kt")
      targetExclude("${layout.buildDirectory}/**/*.kt")
      ktlint(libs.versions.ktlint.get())
        .setEditorConfigPath("../.editorconfig")
    }

    kotlinGradle {
      target("**/*.gradle.kts")
      ktlint(libs.versions.ktlint.get())
        .setEditorConfigPath("../.editorconfig")
    }
  }
}
