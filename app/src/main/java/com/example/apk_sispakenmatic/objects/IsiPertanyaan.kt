package com.example.apk_sispakenmatic.objects

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IsiPertanyaan(
    var id: Int,
    var pertanyaan:String,
    var isTrue: Boolean
) : Parcelable
