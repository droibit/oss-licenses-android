package com.github.droibit.oss_licenses

import app.cash.licensee.LicenseeTask
import com.android.build.api.variant.AndroidComponentsExtension
import java.util.Locale
import org.gradle.api.Action
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.TaskContainer
import org.gradle.api.tasks.TaskProvider

private const val TRANSFORM_TASK_PREFIX = "transformLicenseeArtifacts"
private const val ROOT_TRANSFORM_TASK = "transformLicenseeArtifacts"
private const val LICENSEE_TASK_PREFIX = "licenseeAndroid"

class LicenseeBridgePlugin : Plugin<Project> {
  override fun apply(target: Project) {
    if (!target.pluginManager.hasPlugin("app.cash.licensee")) {
      throw GradleException(
        "The 'app.cash.licensee' plugin must be applied before this plugin. " +
          "See: https://github.com/cashapp/licensee#usage for setup instructions.",
      )
    }

    target.pluginManager.withPlugin("com.android.application") {
      val rootTask = target.maybeRegisterRootTask()
      target.configureAndroidPlugin(rootTask)
    }
  }

  private fun Project.configureAndroidPlugin(rootTask: TaskProvider<Task>) {
    val androidComponents =
      extensions.getByType(AndroidComponentsExtension::class.java)
    androidComponents.onVariants { variant ->
      val variantName = variant.name.replaceFirstChar { it.titlecase(Locale.ROOT) }
      val licenseeTask = tasks.named(
        "$LICENSEE_TASK_PREFIX$variantName",
        LicenseeTask::class.java,
      )
      val transformTask = tasks.maybeRegister<TransformLicenseeArtifactsTask>(
        "$TRANSFORM_TASK_PREFIX$variantName",
      ) { task ->
        task.description =
          "Transforms the JSON output of licensee plugin into Android resource files for $variantName"
        task.inputFile.set(licenseeTask.flatMap { it.jsonOutput })
      }

      rootTask.configure { task ->
        task.dependsOn(transformTask)
      }

      variant.sources.res?.addGeneratedSourceDirectory(
        transformTask,
        TransformLicenseeArtifactsTask::outputResDir,
      )
    }
  }

  private fun Project.maybeRegisterRootTask(): TaskProvider<Task> =
    if (tasks.findByName(ROOT_TRANSFORM_TASK) != null) {
      tasks.named(ROOT_TRANSFORM_TASK)
    } else {
      tasks.register(ROOT_TRANSFORM_TASK) { task ->
        task.description = "Transforms all Licensee artifacts into Android resource files"
      }
    }

  private inline fun <reified T : Task> TaskContainer.maybeRegister(
    name: String,
    configurationAction: Action<T>,
  ): TaskProvider<T> = if (findByName(name) == null) {
    register(name, T::class.java, configurationAction)
  } else {
    named(name, T::class.java)
  }
}
