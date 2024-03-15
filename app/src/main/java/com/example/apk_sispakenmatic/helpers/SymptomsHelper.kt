package com.example.apk_sispakenmatic.helpers

import com.example.apk_sispakenmatic.objects.ArrayGejala
import com.example.apk_sispakenmatic.objects.IsiPertanyaan

object SymptomsHelper {
    fun generateBagian():ArrayList<ArrayGejala>{
        val arrayBagian : ArrayList<ArrayGejala> =ArrayList() //pilihangejala
        val arrayPertanyaan1: ArrayList<IsiPertanyaan> = ArrayList() //arayy pertanyaan
        val arrayPertanyaan2: ArrayList<IsiPertanyaan> = ArrayList()

         arrayPertanyaan1.add(
            IsiPertanyaan(
                1,
                "Kick Starter terasa ringan",
                false
            )
        )
        arrayPertanyaan1.add(
            IsiPertanyaan(
                2,
                "Kabel Coil tidak mengeluarkan arus listrik",
                false
            )
        )
        arrayPertanyaan1.add(
            IsiPertanyaan(
                3,
                "Bensin masih ada/tersisa",
                false
            )
        )
        arrayPertanyaan2.add(
            IsiPertanyaan(
                1,
                "Kondisi Tensioner masih normal",
                false
            )
        )
        arrayPertanyaan2.add(
            IsiPertanyaan(
                2,
                "Oli Kotor dan terdapat pecahan karet",
                false
            )
        )

        //pilihan gejala
        arrayBagian.add(
            ArrayGejala(
                1,
                "Mesin Tidak Mau Hidup",
                arrayPertanyaan1
            )
        )
        arrayBagian.add(
            ArrayGejala(
                2,
                "Timbul Suara Bergemericik Pada Mesin",
                arrayPertanyaan2
            )
        )

        return arrayBagian

    }
}