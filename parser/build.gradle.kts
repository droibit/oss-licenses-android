plugins {
  alias(libs.plugins.osslicenses.android.library)
  alias(libs.plugins.osslicenses.android.maven.publish)
}

android {
  namespace = "com.github.droibit.oss_licenses.parser"

  defaultConfig.minSdk = 21
}

dependencies {
  api(platform(libs.kotlin.coroutines.bom))
  api(libs.kotlin.coroutines.core)

  implementation(libs.androidx.annotation)
  implementation(libs.androidx.core)
  implementation(libs.okio)

  testImplementation(libs.junit)
  testImplementation(libs.truth)
  testImplementation(libs.mockk)
  testImplementation(libs.kotlin.coroutines.test)
}
