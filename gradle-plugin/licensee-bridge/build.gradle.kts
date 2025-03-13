@file:Suppress("UnstableApiUsage")

import com.vanniktech.maven.publish.GradlePlugin
import com.vanniktech.maven.publish.JavadocJar
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
  alias(libs.plugins.java.gradle)
  alias(libs.plugins.jvm.test.suite)
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.maven.publish)
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
  compilerOptions {
    apiVersion.set(KotlinVersion.KOTLIN_1_8)
    jvmTarget.set(JvmTarget.JVM_11)
  }
}

gradlePlugin {
  plugins {
    register("licenseeBridge") {
      id = libs.plugins.licensee.bridge
        .get()
        .pluginId
      displayName = "Licensee Bridge"
      description = "A Gradle plugin that transforms Licensee artifacts to a format compatible with the Google Play OSS Licenses plugin."
      implementationClass = "com.github.droibit.oss_licenses.LicenseeBridgePlugin"
    }
  }
}

dependencies {
  implementation(gradleApi())

  compileOnly(libs.gradle.plugin.android)
  compileOnly(libs.gradle.plugin.kotlin)
  compileOnly(libs.gradle.plugin.licensee)

  implementation(platform(libs.kotlin.serialization.bom))
  implementation(libs.kotlin.serialization.json)
  implementation(libs.kotlin.serialization.json.okio)
  implementation(libs.okio)
}

testing {
  suites {
    val test by getting(JvmTestSuite::class) {
      useJUnitJupiter()

      dependencies {
        implementation(libs.truth)
        implementation(libs.mockk)
        implementation(libs.gradle.plugin.licensee)
      }
    }

    val integrationTest by registering(JvmTestSuite::class) {
      useJUnitJupiter()

      dependencies {
        implementation(gradleTestKit())
        implementation(libs.truth)
        implementation(libs.gradle.plugin.licensee)
        implementation(libs.okio)
      }

      targets.all {
        testTask.configure {
          mustRunAfter(test)
          group = "verification"
          description = "Runs the integration tests."

          // ref. https://github.com/gradle/gradle/issues/22765#issuecomment-1444363323
          jvmArgs(
            "--add-opens",
            "java.base/java.util=ALL-UNNAMED",
            "--add-opens",
            "java.base/java.util.concurrent.atomic=ALL-UNNAMED",
            "--add-opens",
            "java.base/java.lang.invoke=ALL-UNNAMED",
            "--add-opens",
            "java.base/java.net=ALL-UNNAMED",
          )

          beforeTest(
            closureOf<TestDescriptor> {
              logger.lifecycle("Running test: ${this.className} ${this.displayName}")
            },
          )
        }
      }
    }
  }
}

tasks.named("check") {
  dependsOn(testing.suites.named("integrationTest"))
}

tasks.withType<ValidatePlugins>().configureEach {
  enableStricterValidation.set(true)
}

mavenPublishing {
  configure(
    GradlePlugin(javadocJar = JavadocJar.None()),
  )
}
