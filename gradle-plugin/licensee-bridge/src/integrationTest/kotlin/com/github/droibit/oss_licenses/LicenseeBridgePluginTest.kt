package com.github.droibit.oss_licenses

import com.github.droibit.oss_licenses.utils.copyGradleWrapperIfNeeded
import com.github.droibit.oss_licenses.utils.copyLocalProperties
import com.github.droibit.oss_licenses.utils.fixturesDir
import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assertWithMessage
import java.io.File
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LicenseeBridgePluginTest {
  @ParameterizedTest
  @ValueSource(
    strings = [
      "plugin-android-application",
      "plugin-android-application-min-supported-version",
      "plugin-android-application-product-flavors",
      "plugin-licensee-min-supported-version",
    ],
  )
  fun runSuccessfully(fixtureName: String) {
    val fixtureDir = File(fixturesDir, fixtureName).also {
      copyGradleWrapperIfNeeded(it, sourceWrapperDir = File("../gradle/wrapper"))
      copyLocalProperties(it)
    }
    createRunner(fixtureDir, isFirstRun = true).build()
    verifyGeneratedFiles(fixtureDir)

    // Ensure up-to-date functionality works.
    val secondRun = createRunner(fixtureDir, isFirstRun = false).build()
    secondRun.tasks
      .filter {
        it.path.contains(":transformLicenseeArtifacts")
      }.also {
        assertThat(it).isNotEmpty()
      }.forEach { task ->
        assertThat(task.outcome).isEqualTo(TaskOutcome.UP_TO_DATE)
      }
  }

  @Test
  fun runFailsWithoutLicenseePlugin() {
    val fixtureDir = File(fixturesDir, "plugin-licensee-not-applied").also {
      copyGradleWrapperIfNeeded(it, sourceWrapperDir = File("../gradle/wrapper"))
      copyLocalProperties(it)
    }
    val result = createRunner(fixtureDir, isFirstRun = true).buildAndFail()
    assertWithMessage("Build failed with output: ${result.output}")
      .that(result.output)
      .contains(
        "The 'app.cash.licensee' plugin must be applied before this plugin. See: https://github.com/cashapp/licensee#usage for setup instructions.",
      )
  }

  private fun createRunner(fixtureDir: File, isFirstRun: Boolean): GradleRunner {
    val arguments = buildList {
      if (isFirstRun) {
        addAll(listOf("clean", "assemble", "--continue"))
      } else {
        add("transformLicenseeArtifacts")
      }
      addAll(listOf("--stacktrace", "--configuration-cache"))
    }
    return GradleRunner
      .create()
      .withProjectDir(fixtureDir)
      .withDebug(true) // Run in-process
      .withArguments(arguments)
      .forwardOutput()
  }

  private fun verifyGeneratedFiles(fixtureDir: File) {
    val expectedDir = File(fixtureDir, "expected/build/generated/res")
    val actualDir = File(fixtureDir, "build/generated/res").also {
      assertWithMessage("Res directory doesn't exist': ${it.path}")
        .that(it.exists())
        .isTrue()
    }

    val expectedVariantDirs = expectedDir.listFiles()
    assertThat(expectedVariantDirs).isNotEmpty()

    expectedVariantDirs?.forEach { expectedVariantDir ->
      val variantDirName = expectedVariantDir.name
      val actualVariantDir = File(actualDir, variantDirName).also {
        assertWithMessage("Variant directory doesn't exist: ${it.path}")
          .that(it.exists())
          .isTrue()
      }

      val expectedFiles = File(expectedVariantDir, "raw").listFiles()
      assertThat(expectedFiles).isNotEmpty()

      val actualRawDir = File(actualVariantDir, "raw").also {
        assertWithMessage("Raw directory doesn't exist: ${it.path}")
          .that(it.exists())
          .isTrue()
      }

      expectedFiles?.forEach { expectedFile ->
        val actualFile = File(actualRawDir, expectedFile.name)
        assertWithMessage("File missing: ${actualFile.path}")
          .that(File(actualRawDir, expectedFile.name).exists())
          .isTrue()

        val expectedContent = expectedFile.readText()
        val actualContent = actualFile.readText()
        assertWithMessage("Content mismatch in file: ${actualFile.path}")
          .that(actualContent)
          .isEqualTo(expectedContent)
      }
    }
  }
}
