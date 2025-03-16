package com.github.droibit.oss_licenses.ui.compose.material3.internal

import android.util.Patterns
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.github.droibit.oss_licenses.ui.OssLicenseUiState

@Composable
internal fun OssLicenseDetail(
  license: OssLicenseUiState,
  modifier: Modifier = Modifier,
  showLibraryName: Boolean = true,
  scrollState: ScrollState = rememberScrollState(),
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .verticalScroll(scrollState),
  ) {
    if (showLibraryName) {
      Text(
        text = license.library,
        style = MaterialTheme.typography.titleLarge.copy(
          color = MaterialTheme.colorScheme.onSurfaceVariant,
        ),
        modifier = Modifier
          .padding(horizontal = 16.dp)
          .padding(top = 16.dp),
      )
    }

    LinkableText(
      text = license.licenseText,
      modifier = Modifier.padding(
        horizontal = 16.dp,
        vertical = 16.dp,
      ),
    )
  }
}

@Composable
private fun LinkableText(
  text: String,
  modifier: Modifier = Modifier,
  linkColor: Color = LocalContentColor.current,
) {
  val annotatedText = remember(text) {
    buildAnnotatedString {
      append(text)

      val foundUrls = Patterns.WEB_URL.toRegex().findAll(text)
      foundUrls.forEach { urlMatch ->
        val startIndex = text.indexOf(urlMatch.value)
        val endIndex = startIndex + urlMatch.value.length
        addLink(
          url = LinkAnnotation.Url(
            urlMatch.value,
            styles = TextLinkStyles(
              style = SpanStyle(
                color = linkColor,
                textDecoration = TextDecoration.Underline,
              ),
              pressedStyle = SpanStyle(
                color = linkColor.copy(alpha = 0.6f),
                textDecoration = TextDecoration.Underline,
              ),
            ),
          ),
          start = startIndex,
          end = endIndex,
        )
      }
    }
  }

  Text(
    text = annotatedText,
    style = MaterialTheme.typography.bodyMedium.copy(
      fontFamily = FontFamily.Monospace,
    ),
    modifier = modifier,
  )
}
