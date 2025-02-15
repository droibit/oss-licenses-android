import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.LibraryExtension
import com.github.droibit.oss_licenses.configureAndroidCompose
import com.github.droibit.oss_licenses.configureAndroidWearCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

class AndroidWearComposeConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      apply(plugin = "org.jetbrains.kotlin.plugin.compose")

      pluginManager.withPlugin("com.android.library") {
        extensions.getByType<LibraryExtension>().apply {
          configureAndroidWearCompose(this)

          defaultConfig.minSdk = 25
        }
      }

      pluginManager.withPlugin("com.android.application") {
        extensions.getByType<ApplicationExtension>().apply {
          configureAndroidCompose(this)
        }
      }
    }
  }
}
