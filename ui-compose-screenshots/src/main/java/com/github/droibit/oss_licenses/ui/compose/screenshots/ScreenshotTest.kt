@file:OptIn(ExperimentalRoborazziApi::class)

package com.github.droibit.oss_licenses.ui.compose.screenshots

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.DarkMode
import androidx.compose.ui.test.DeviceConfigurationOverride
import androidx.compose.ui.test.FontScale
import androidx.compose.ui.test.Locales
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.then
import androidx.compose.ui.text.intl.LocaleList
import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.RoborazziOptions
import com.github.takahirom.roborazzi.ThresholdValidator
import com.github.takahirom.roborazzi.captureScreenRoboImage
import org.junit.Rule
import org.junit.rules.TestName
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(ParameterizedRobolectricTestRunner::class)
@Config(sdk = [35])
@GraphicsMode(GraphicsMode.Mode.NATIVE)
abstract class ScreenshotTest(val device: Device) {
  @get:Rule
  val composeRule: ComposeContentTestRule = createComposeRule()

  @get:Rule
  val testInfo: TestName = TestName()

  open fun getFileName(suffix: String = ""): String = "${this.javaClass.simpleName}_${
    testInfo.methodName.substringBefore(
      '[',
    )
  }${suffix}_${device.name.lowercase()}.png"

  open val roborazziOptions = RoborazziOptions(
    recordOptions = RoborazziOptions.RecordOptions(
      // Reduce the size of the PNGs
      resizeScale = 0.5,
      applyDeviceCrop = true,
    ),
    compareOptions = RoborazziOptions.CompareOptions(
      resultValidator = ThresholdValidator(0.02f),
    ),
  )

  fun runScreenshotTest(
    captureEnd: (() -> Unit)? = null,
    content: @Composable () -> Unit,
  ) {
    RuntimeEnvironment.setQualifiers(device.qualifier)

    composeRule.setContent {
      CompositionLocalProvider(
        LocalInspectionMode provides true,
      ) {
        DeviceConfigurationOverride(
          DeviceConfigurationOverride.FontScale(device.fontScale) then
            DeviceConfigurationOverride.Locales(
              if (device.locale != null) LocaleList(device.locale) else LocaleList.current,
            ) then
            DeviceConfigurationOverride.DarkMode(device.isDarkMode),
        ) {
          content()
        }
      }
    }

    captureScreenshot()

    if (captureEnd != null) {
      captureEnd()

      composeRule.waitForIdle()

      captureScreenshot("_end")
    }
  }

  private fun captureScreenshot(suffix: String = "") {
    val fileName = getFileName(suffix)

    captureScreenRoboImage(
      filePath = "src/test/screenshots/$fileName",
      roborazziOptions = roborazziOptions,
    )
  }

  companion object {
    @JvmStatic
    @ParameterizedRobolectricTestRunner.Parameters
    fun devices(): List<Device> = Device.entries
  }
}

enum class Device(
  val qualifier: String,
  val fontScale: Float = 1f,
  val isDarkMode: Boolean = false,
  val locale: String? = null,
) {
  Phone(RobolectricDeviceQualifiers.MediumPhone),
  PhoneDark(RobolectricDeviceQualifiers.MediumPhone, isDarkMode = true),
  PhoneJapanese(RobolectricDeviceQualifiers.MediumPhone, locale = "ja"),
  Tablet(RobolectricDeviceQualifiers.MediumTablet),
  TabletLargeFonts(RobolectricDeviceQualifiers.MediumTablet, fontScale = 1.24f),
}
