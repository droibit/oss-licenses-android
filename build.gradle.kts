import com.android.build.gradle.BaseExtension
import com.android.build.gradle.BasePlugin
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

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

  plugins.whenPluginAdded {
    if (this is BasePlugin) {
      project.extensions.getByType<BaseExtension>().apply {
        compileOptions {
          sourceCompatibility = JavaVersion.VERSION_17
          targetCompatibility = JavaVersion.VERSION_17
        }
      }

      spotless {
        kotlin {
          target("**/*.kt")
          targetExclude("${layout.buildDirectory}/**/*.kt")
          targetExclude("**/generated/**/*.kt")
          ktlint(libs.versions.ktlint.get())
            .editorConfigOverride(
              mapOf(
                "ktlint_function_naming_ignore_when_annotated_with" to "Composable, Test",
                "ktlint_standard_package-name" to "disabled",
              ),
            )
            .customRuleSets(
              listOf(
                libs.ktlint.compose.rules.get().toString(),
              )
            )
        }

        format("xml") {
          target("**/*.xml")
          targetExclude("**/build/**/*.xml")

          trimTrailingWhitespace()
          indentWithSpaces(2)
          endWithNewline()
        }
      }
    }

    tasks.withType<KotlinJvmCompile>().configureEach {
      compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
      }
    }
  }
}
