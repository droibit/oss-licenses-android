import com.android.build.gradle.LibraryExtension
import com.github.droibit.oss_licenses.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      apply(plugin = "com.android.library")
      apply(plugin = "org.jetbrains.kotlin.android")

      extensions.configure<LibraryExtension> {
        configureKotlinAndroid(this)

        defaultConfig {
          testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        packaging {
          resources {
            excludes += listOf(
              "/META-INF/{AL2.0,LGPL2.1}",
              "/META-INF/core_debug.kotlin_module",
            )
          }
        }
      }
    }
  }
}
