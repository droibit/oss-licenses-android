import com.android.build.api.dsl.ApplicationExtension
import com.github.droibit.oss_licenses.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      apply(plugin = "com.android.application")
      apply(plugin = "org.jetbrains.kotlin.android")

      extensions.configure<ApplicationExtension> {
        configureKotlinAndroid(this)

        defaultConfig {
          versionCode = 1
          versionName = "1.0.0"
          testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildFeatures {
          buildConfig = true
        }

        buildTypes {
          debug {
            debug {
              isDebuggable = true
              isMinifyEnabled = false
              isShrinkResources = false
              proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
              )
            }

            release {
              isMinifyEnabled = true
              isShrinkResources = true
              proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
              )

              // Allow testing the release build type with `./gradlew XXXX-mobile:installRelease`
              signingConfig = signingConfigs.getByName("debug")
            }
          }
        }
      }
    }
  }
}
