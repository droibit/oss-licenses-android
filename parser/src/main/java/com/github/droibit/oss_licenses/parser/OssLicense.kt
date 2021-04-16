package com.github.droibit.oss_licenses.parser

import java.io.Serializable

data class OssLicense(
    val libraryName: String,
    val license: String
) : Serializable