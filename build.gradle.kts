import com.android.build.gradle.BasePlugin
import com.android.build.gradle.BaseExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
  dependencies {
    classpath(libs.plugin.ossLicense)
  }
}

plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.kotlin) apply false
  alias(libs.plugins.spotless)
}

subprojects {
  apply(plugin = "com.diffplug.spotless")

  plugins.whenPluginAdded {
    if (this is BasePlugin) {
      project.extensions.getByType<BaseExtension>().apply {
        compileOptions {
          sourceCompatibility = JavaVersion.VERSION_1_8
          targetCompatibility = JavaVersion.VERSION_1_8
        }
      }

      spotless {
        kotlin {
          target("**/*.kt")
          targetExclude("$buildDir/**/*.kt")
          targetExclude("**/generated/**/*.kt")
          // Spotless doesn't read .editorconfig yet: https://github.com/diffplug/spotless/issues/142
          ktlint(libs.versions.ktlint.get()).editorConfigOverride(mapOf(
            "disabled_rules"                              to "package-name",
            "insert_final_newline"                        to "true",
            "end_of_line"                                 to "lf",
            "charset"                                     to "utf-8",
            "indent_size"                                 to "2",
            "ij_kotlin_allow_trailing_comma"              to "true",
            "ij_kotlin_allow_trailing_comma_on_call_site" to "true",
            "ij_kotlin_imports_layout"                    to "*",
          ))
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

    tasks.withType(KotlinCompile::class) {
      kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
      }
    }
  }
}
