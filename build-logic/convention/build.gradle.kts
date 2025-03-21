import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  `kotlin-dsl`
}

group = "com.github.droibit.oss_licenses.buildlogic"

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
  compilerOptions {
    jvmTarget = JvmTarget.JVM_17
  }
}

dependencies {
  compileOnly(libs.gradle.plugin.android)
  compileOnly(libs.gradle.plugin.kotlin)
  compileOnly(libs.gradle.plugin.maven.publish)
}

gradlePlugin {
  plugins {
    register("androidApplication") {
      id = libs.plugins.osslicenses.android.application
        .get()
        .pluginId
      implementationClass = "AndroidApplicationConventionPlugin"
    }

    register("androidLibrary") {
      id = libs.plugins.osslicenses.android.library
        .get()
        .pluginId
      implementationClass = "AndroidLibraryConventionPlugin"
    }

    register("androidCompose") {
      id = libs.plugins.osslicenses.android.compose
        .get()
        .pluginId
      implementationClass = "AndroidComposeConventionPlugin"
    }

    register("androidWearCompose") {
      id = libs.plugins.osslicenses.android.wear.compose
        .get()
        .pluginId
      implementationClass = "AndroidWearComposeConventionPlugin"
    }

    register("androidMavenPublish") {
      id = libs.plugins.osslicenses.android.maven.publish
        .get()
        .pluginId
      implementationClass = "AndroidMavenPublishConventionPlugin"
    }
  }
}

tasks.withType<ValidatePlugins>().configureEach {
  enableStricterValidation.set(true)
}
