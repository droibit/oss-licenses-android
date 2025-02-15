plugins {
  alias(libs.plugins.osslicenses.android.library)
}

android {
  namespace = "com.github.droibit.oss_licenses.ui.viewmodel"

  defaultConfig.minSdk = 24
}

dependencies {
  api(projects.parser)

  api(platform(libs.kotlin.coroutines.bom))
  api(libs.kotlin.coroutines.core)
  api(libs.androidx.lifecycle.viewModel)

  implementation(libs.androidx.annotation)

  testImplementation(libs.junit)
  testImplementation(libs.truth)
  testImplementation(libs.mockk)
  testImplementation(libs.turbine)
  testImplementation(libs.kotlin.coroutines.test)
}

apply(from = "$rootDir/gradle/gradle-mvn-push.gradle.kts")
