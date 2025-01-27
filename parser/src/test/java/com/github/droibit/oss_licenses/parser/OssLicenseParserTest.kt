package com.github.droibit.oss_licenses.parser

import com.google.common.truth.Truth.assertThat
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit4.MockKRule
import okio.source
import org.junit.Rule
import org.junit.Test

class OssLicenseParserTest {
  @get:Rule
  val mockkRule = MockKRule(this)

  @InjectMockKs
  private lateinit var ossLicenseParser: OssLicenseParser

  @Test
  fun parse_withRealData() {
    val licenseMetadata = """
    0:46 Compose Animation Core
    0:46 Compose Tooling Data
    0:46 Compose Util
    0:46 Compose UI
    0:46 Android Lifecycle Kotlin Extensions
    47:47 JetBrains Java Annotations
    47:47 kotlinx-coroutines-android
    47:47 Dagger
    47:47 kotlinx-coroutines-core
    47:47 kotlinx-coroutines-bom
    """.trimIndent()
    val licenses = """
    http://www.apache.org/licenses/LICENSE-2.0.txt
    https://www.apache.org/licenses/LICENSE-2.0.txt
    """.trimIndent()
    val licenseMetadataSource = licenseMetadata.byteInputStream().source()
    val licensesSource = licenses.byteInputStream().source()

    val result = ossLicenseParser.parse(licensesSource, licenseMetadataSource, emptySet())
    assertThat(result).containsExactly(
      OssLicense(
        "Android Lifecycle Kotlin Extensions",
        "http://www.apache.org/licenses/LICENSE-2.0.txt",
      ),
      OssLicense("Compose Animation Core", "http://www.apache.org/licenses/LICENSE-2.0.txt"),
      OssLicense("Compose Tooling Data", "http://www.apache.org/licenses/LICENSE-2.0.txt"),
      OssLicense("Compose UI", "http://www.apache.org/licenses/LICENSE-2.0.txt"),
      OssLicense("Compose Util", "http://www.apache.org/licenses/LICENSE-2.0.txt"),
      OssLicense("Dagger", "https://www.apache.org/licenses/LICENSE-2.0.txt"),
      OssLicense("JetBrains Java Annotations", "https://www.apache.org/licenses/LICENSE-2.0.txt"),
      OssLicense("kotlinx-coroutines-android", "https://www.apache.org/licenses/LICENSE-2.0.txt"),
      OssLicense("kotlinx-coroutines-bom", "https://www.apache.org/licenses/LICENSE-2.0.txt"),
      OssLicense("kotlinx-coroutines-core", "https://www.apache.org/licenses/LICENSE-2.0.txt"),
    ).inOrder()
  }

  @Test
  fun parse_withIgnoreLibraries() {
    val licenseMetadata = "0:8 library1\n9:8 library2"
    val licenses = "license1\nlicense2"
    val licenseMetadataSource = licenseMetadata.byteInputStream().source()
    val licensesSource = licenses.byteInputStream().source()

    val result = ossLicenseParser.parse(licensesSource, licenseMetadataSource, setOf("library1"))
    assertThat(result).containsExactly(
      OssLicense("library2", "license2"),
    )
  }

  @Test
  fun parse_withEmptyResources() {
    val emptySource = "".byteInputStream().source()
    val result = ossLicenseParser.parse(emptySource, emptySource)
    assertThat(result).isEmpty()
  }
}
