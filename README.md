# OSS Licenses
[![jitpack.io](https://jitpack.io/v/droibit/oss-licenses-android.svg)](https://jitpack.io/#droibit/oss-licenses-android)

- WIP

## Download
Add the following code to your build.gradle.

```gradle
allprojects {
  repositories {
    ...
    maven {
        url = URI("https://jitpack.io")
        content {
            includeGroup("com.github.droibit.oss-licenses-android")
        }
    }
  }
}

dependencies {
    implementation 'com.github.droibit.oss-licenses-android:parser:0.6.0'
    // or
    implementation 'com.github.droibit.oss-licenses-android:ui-compose-material3:0.6.0'
    // or
    implementation 'com.github.droibit.oss-licenses-android:ui-wear-compose-material:0.6.0'
    // or
    implementation 'com.github.droibit.oss-licenses-android:ui-wear-compose-material3:0.6.0'
}
```

## License

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
