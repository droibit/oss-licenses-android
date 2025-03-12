# Licensee Bridge Gradle Plugin

[![Gradle Plugin CI](https://github.com/droibit/oss-licenses-android/actions/workflows/gradle-plugin.yml/badge.svg)](https://github.com/droibit/oss-licenses-android/actions/workflows/gradle-plugin.yml)
[![Software License](https://img.shields.io/badge/license-Apache%202.0-brightgreen.svg)](https://github.com/droibit/prefbinding/blob/develop/LICENSE)

A Gradle plugin that transforms [Licensee](https://github.com/cashapp/licensee) Gradle Plugin artifacts to a format compatible with the [OSS Licenses plugin](https://github.com/google/play-services-plugins/tree/main/oss-licenses-plugin).

This plugin automatically transforms the license data collected by Licensee during your app's build process into two text files:

- `third_party_licenses`
- `third_party_licenses_metadata`

These files are registered as raw resources so they can be consumed by the oss-licenses-android UI components, play-services-oss-licenses library, and other compatible libraries.

## To Use

### Requirements

- [Licensee Gradle Plugin](https://github.com/cashapp/licensee) 1.11.0 or higher
- Android Gradle Plugin 7.4.0 or higher

### Add the plugins to your project

The plugin is available from the Maven Central repository.

In your app-level build script, you must apply the Licensee Bridge Gradle Plugin **after** the following plugins:

- `com.android.application`
- [`app.cash.licensee`](https://github.com/cashapp/licensee/tree/trunk#usage)

This ensures that the plugin can properly integrate with both the Android and Licensee build systems.

<table>
<tr><td>plugins</td><td>buildscript</td></tr>
<tr><td>

root-level `build.gradle.kts`

```kotlin
plugins {
  ...  
  id("io.github.droibit.oss-licenses-android.licensee-bridge") version "<version>" apply false
}
```

app-level `build.gradle.kts`

```kotlin
plugins {
  ...  
  id("io.github.droibit.oss-licenses-android.licensee-bridge")
}
```

</td><td>

root-level `build.gradle.kts`

```kotlin
buildscript {
  dependencies {
    ...
    classpath("io.github.droibit.oss-licenses-android:licensee-bridge-gradle-plugin:<version>")
  }
}
```

app-level `build.gradle.kts`

```kotlin
plugins {
  ...  
  apply(plugin = "io.github.droibit.oss-licenses-android.licensee-bridge")
}
```

</td></tr>
</table>

For detailed information on how to configure Licensee, refer to:

- [Licensee Configuration](https://github.com/cashapp/licensee#configuration)

### Displaying license information

After setting up this plugin, you can choose from several libraries to display the license information in your application:

- [OSS Licenses Android](../../README.md#overview)
- [play-services-oss-licenses](https://developers.google.com/android/guides/opensource#add-library)
- Other compatible libraries
