@file:OptIn(ExperimentalRoborazziApi::class)

package com.github.droibit.oss_licenses.ui.compose.screenshots

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
abstract class WearScreenshotTest(private val device: WearDevice) {
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
    fun devices(): List<WearDevice> = WearDevice.entries
  }
}

enum class WearDevice(
  val qualifier: String,
  val fontScale: Float = 1f,
  val locale: String? = null,
) {
  SmallRound(RobolectricDeviceQualifiers.WearOSSmallRound),
  LargeRound(RobolectricDeviceQualifiers.WearOSLargeRound),
  SmallRoundLargeFonts(RobolectricDeviceQualifiers.WearOSSmallRound, fontScale = 1.24f),
  SmallRoundJapanese(RobolectricDeviceQualifiers.WearOSSmallRound, locale = "ja"),
}
