# OSS Licenses

[![jitpack.io](https://jitpack.io/v/droibit/oss-licenses-android.svg)](https://jitpack.io/#droibit/oss-licenses-android)

A collection of UI libraries and a parser for displaying open-source licenses in Android applications.  
Built with Jetpack Compose and compatible with the [OSS Licenses Gradle Plugin](https://github.com/google/play-services-plugins/tree/main/oss-licenses-plugin).

## Overview

This library provides several modules to help you display open-source licenses in your applications:

- [ui-compose-material3](./ui-compose-material3): UI components for mobile apps([adaptive apps](https://developer.android.com/adaptive-apps)) using Material 3 design system
- [ui-wear-compose-material](./ui-wear-compose-material): UI components for Wear OS apps using Wear Material design system
- [ui-wear-compose-material3](./ui-wear-compose-material3): UI components for Wear OS apps using Wear Material 3 design system
- [parser](./parser): Core module that parses license information generated by the [OSS Licenses Gradle Plugin](https://github.com/google/play-services-plugins/tree/main/oss-licenses-plugin)

Each module can be used independently based on your app's needs.

## Installation

### Add OSS Licenses Gradle Plugin

First, add the OSS Licenses Gradle Plugin to your project by following the [official documentation](https://github.com/google/play-services-plugins/tree/main/oss-licenses-plugin#add-the-gradle-plugin).  

This plugin is required to generate the license information that this library will display.

### Add the library to your app

Add the following code to your build.gradle.

```gradle
allprojects {
  repositories {
    ...
    maven {
      url 'https://jitpack.io'
      content {
          includeGroup 'com.github.droibit.oss-licenses-android'
      }
    }
  }
}

dependencies {
  // For apps using `androidx.compose.material3:material3`
  implementation 'com.github.droibit.oss-licenses-android:ui-compose-material3:0.6.0'

  // UI components for Wear OS apps
  // If using `androidx.wear.compose:compose-material`
  implementation 'com.github.droibit.oss-licenses-android:ui-wear-compose-material:0.6.0'
  // or
  // If using `androidx.wear.compose:compose-material3`
  implementation 'com.github.droibit.oss-licenses-android:ui-wear-compose-material3:0.6.0'

  // For custom implementations, use only the parser
  implementation 'com.github.droibit.oss-licenses-android:parser:0.6.0'
}
```

## Usage

### Mobile Apps

Launch the license screen in mobile apps using Compose Material 3:

```kotlin
import com.github.droibit.oss_licenses.ui.compose.material3.OssLicensesActivity

startActivity(OssLicensesActivity.createIntent(context))
```

#### Migrating from `play-services-oss-licenses`

If you're currently using the [Google Play Services OSS Licenses library](https://developers.google.com/android/guides/opensource), you can migrate to this library by:

1. Remove the dependency:

```diff
-implementation 'com.google.android.gms:play-services-oss-licenses'
```

2. Update your code to use the new Activity:

```diff
-import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
+import com.github.droibit.oss_licenses.ui.compose.material3.OssLicensesActivity

-startActivity(Intent(context, OssLicensesMenuActivity::class.java));
+startActivity(OssLicensesActivity.createIntent(context))
```

### Wear OS Apps

Launch the license screen in Wear OS apps using your preferred design system:

> [!TIP]  
> The `WearableOssLicensesActivity` supports the `com.google.android.gms.oss.licenses.ACTION_SHOW_LICENSE` intent action, allowing it to be launched from the app info screen on some modern Wear OS devices.

#### Using Material Design

Use the Wear Compose implementation:

```kotlin
import com.github.droibit.oss_licenses.ui.wear.compose.material.WearableOssLicensesActivity

startActivity(WearableOssLicensesActivity.createIntent(context))
```

#### Using Material Design 3

Use the Wear Compose Material3 implementation:

```kotlin
import com.github.droibit.oss_licenses.ui.wear.compose.material3.WearableOssLicensesActivity

startActivity(WearableOssLicensesActivity.createIntent(context))
```

## Contributions

Contributions are welcome! Please feel free to submit a Pull Request.  
Also check out our [development guide](./DEVELOPMENT.md) for details on building and testing the project.

## License

```plaintext
Copyright (C) 2018 Shinya Kumagai

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
