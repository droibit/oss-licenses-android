plugins {
  alias(libs.plugins.spotless)
}

subprojects {
  apply(plugin = "com.diffplug.spotless")

  spotless {
    kotlin {
      target("**/*.kt")
      targetExclude("${layout.buildDirectory}/**")
      ktlint(libs.versions.ktlint.get())
        .setEditorConfigPath("../../.editorconfig")
    }

    kotlinGradle {
      target("**/*.gradle.kts")
      targetExclude("${layout.buildDirectory}/**")
      ktlint(libs.versions.ktlint.get())
        .setEditorConfigPath("../../.editorconfig")
    }
  }
}
