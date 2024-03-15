package com.example.apk_sispakenmatic.objects

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArrayGejala(
    var id :Int,
    var gejala: String,
    var arrays: ArrayList<IsiPertanyaan>
) : Parcelable
