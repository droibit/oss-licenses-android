@file:OptIn(ExperimentalRoborazziApi::class)

package com.github.droibit.oss_licenses.ui.wear.compose.material.internal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
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
@Config(
  sdk = [34],
  qualifiers = RobolectricDeviceQualifiers.WearOSSmallRound,
)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
abstract class WearScreenshotTest(val device: Device) {

  @get:Rule
  val composeRule: ComposeContentTestRule = createComposeRule()

  @get:Rule
  val testInfo: TestName = TestName()

  fun runScreenshotTest(
    content: @Composable () -> Unit,
  ) {
    RuntimeEnvironment.setQualifiers(device.qualifier)
    RuntimeEnvironment.setFontScale(device.fontScale)
    if (device.locale != null) {
      RuntimeEnvironment.setQualifiers("+${device.locale}")
    }

    composeRule.setContent {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(Color.Black),
      ) {
        content()
      }
    }

    captureScreenRoboImage(
      filePath = "src/test/screenshots/${this.javaClass.simpleName}_${
        testInfo.methodName.substringBefore(
          '[',
        )
      }_${device.name.lowercase()}.png",
      roborazziOptions = RoborazziOptions(
        recordOptions = RoborazziOptions.RecordOptions(
          applyDeviceCrop = true,
        ),
        compareOptions = RoborazziOptions.CompareOptions(
          resultValidator = ThresholdValidator(0.02f),
        ),
      ),
    )
  }

  companion object {
    @JvmStatic
    @ParameterizedRobolectricTestRunner.Parameters
    fun devices(): List<Device> = Device.entries
  }
}

enum class Device(val qualifier: String, val fontScale: Float = 1f, val locale: String? = null) {
  SmallRound(RobolectricDeviceQualifiers.WearOSSmallRound),
  LargeRound(RobolectricDeviceQualifiers.WearOSLargeRound),
  SmallRoundLargeFonts(RobolectricDeviceQualifiers.WearOSSmallRound, fontScale = 1.24f),
  SmallRoundJapanese(RobolectricDeviceQualifiers.WearOSSmallRound, locale = "ja"),
}
