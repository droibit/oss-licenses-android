plugins {
  alias(libs.plugins.osslicenses.android.library)
  alias(libs.plugins.osslicenses.android.maven.publish)
}

android {
  namespace = "com.github.droibit.oss_licenses.ui.wear"

  defaultConfig.minSdk = 24
}

dependencies {
  api(projects.uiViewmodel)

  implementation(libs.androidx.annotation)
  implementation(libs.androidx.fragment)
  implementation(libs.androidx.lifecycle.runtime)
  implementation(libs.androidx.lifecycle.viewModel)
  implementation(libs.androidx.recyclerView)
  implementation(libs.androidx.wear)
}
