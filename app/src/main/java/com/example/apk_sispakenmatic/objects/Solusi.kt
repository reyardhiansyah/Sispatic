package com.example.apk_sispakenmatic.objects

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Solusi(
    val name: String?, // NULL-able
    val desc: String?,
    val mitigation: String?,
    val solution: String?
): Parcelable