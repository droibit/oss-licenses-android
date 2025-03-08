plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.compose.compiler) apply false
  alias(libs.plugins.osslicenses) apply false
  alias(libs.plugins.maven.publish) apply false
  alias(libs.plugins.spotless)
  alias(libs.plugins.licensee) apply false
  alias(libs.plugins.licensee.bridge) version "dev" apply false
}

subprojects {
  // TODO: Move Spotless configuration to Convention Plugin once issue is resolved.
  // ref. https://github.com/diffplug/spotless/issues/2388
  apply(plugin = "com.diffplug.spotless")

  spotless {
    kotlin {
      target("**/*.kt")
      targetExclude("${layout.buildDirectory}/**/*.kt")
      targetExclude("**/generated/**/*.kt")
      ktlint(libs.versions.ktlint.get())
        .customRuleSets(
          listOf(
            libs.ktlint.compose.rules.get().toString(),
          ),
        )
    }

    kotlinGradle {
      target("**/*.gradle.kts")
      ktlint(libs.versions.ktlint.get())
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
