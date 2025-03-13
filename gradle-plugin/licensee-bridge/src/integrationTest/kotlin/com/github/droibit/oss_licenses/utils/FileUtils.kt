package com.github.droibit.oss_licenses.utils

import java.io.File
import java.util.Properties

val fixturesDir = File("src/integrationTest/fixtures")

fun copyLocalProperties(fixtureDir: File) {
  val androidSdkFile = File("local.properties")
  if (androidSdkFile.exists()) {
    androidSdkFile.copyTo(File(fixtureDir, "local.properties"), overwrite = true)
  }
}

fun copyGradleWrapperIfNeeded(
  fixtureDir: File,
  sourceWrapperDir: File,
) {
  val fixturePropsFile = File(fixtureDir, "gradle/wrapper/gradle-wrapper.properties")
  val sourcePropsFile = File(sourceWrapperDir, "gradle-wrapper.properties")

  if (shouldCopyGradleWrapper(sourcePropsFile, fixturePropsFile)) {
    val gradleRoot = File(fixtureDir, "gradle").also { it.mkdirs() }
    val wrapperDir = File(gradleRoot, "wrapper").also { it.mkdirs() }
    sourceWrapperDir.copyRecursively(wrapperDir, overwrite = true)
    println("Copied Gradle wrapper from source project to fixture project")
  } else {
    println("Skipped copying Gradle wrapper (versions match or fixture already has wrapper)")
  }
}

private fun shouldCopyGradleWrapper(sourcePropsFile: File, fixturePropsFile: File): Boolean {
  if (!fixturePropsFile.exists()) {
    return true
  }

  val sourceDistUrl = getDistributionUrl(sourcePropsFile)
  val fixtureDistUrl = getDistributionUrl(fixturePropsFile)
  return sourceDistUrl != fixtureDistUrl
}

private fun getDistributionUrl(propertiesFile: File): String {
  val properties = Properties()
  propertiesFile.inputStream().use { properties.load(it) }
  return properties.getProperty("distributionUrl", "").also {
    require(it.isNotBlank()) { "distributionUrl is not found in $propertiesFile" }
  }
}
