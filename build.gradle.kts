buildscript {
  dependencies {
    classpath(libs.plugin.ossLicense)
  }
}

plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.kotlin) apply false
  alias(libs.plugins.compose.compiler) apply false
  alias(libs.plugins.spotless)
}

subprojects {
  apply(plugin = "com.diffplug.spotless")

  spotless {
    kotlin {
      target("**/*.kt")
      targetExclude("${layout.buildDirectory}/**/*.kt")
      targetExclude("**/generated/**/*.kt")
      ktlint(libs.versions.ktlint.get())
        .editorConfigOverride(
          mapOf(
            "ktlint_code_style" to "ktlint_official",
            "ktlint_function_naming_ignore_when_annotated_with" to "Composable, Test",
            "ktlint_standard_package-name" to "disabled",
            "ktlint_standard_multiline-expression-wrapping" to "disabled",
            "ktlint_standard_class-signature" to "disabled",
            "ktlint_standard_function-signature" to "disabled",
          ),
        )
        .customRuleSets(
          listOf(
            libs.ktlint.compose.rules.get().toString(),
          ),
        )
    }

    format("xml") {
      target("**/*.xml")
      targetExclude("**/build/**/*.xml")

      trimTrailingWhitespace()
      leadingTabsToSpaces(2)
      endWithNewline()
    }
  }
}
