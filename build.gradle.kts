import com.android.build.gradle.BaseExtension
import com.android.build.gradle.BasePlugin
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
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

  plugins.withType<BasePlugin> {
    project.extensions.configure<BaseExtension> {
      compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
      }
    }
  }

  tasks.withType<KotlinJvmCompile>().configureEach {
    compilerOptions {
      jvmTarget.set(JvmTarget.JVM_17)

      // Align with Jetpack Compose's Kotlin compatibility requirements.
      plugins.withId(libs.plugins.android.library.get().pluginId) {
        apiVersion.set(KotlinVersion.KOTLIN_1_9)
        languageVersion.set(KotlinVersion.KOTLIN_1_9)
      }
    }
  }
}
