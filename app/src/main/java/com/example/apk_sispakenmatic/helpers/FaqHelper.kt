package com.example.apk_sispakenmatic.helpers

import com.example.apk_sispakenmatic.objects.Faq
import java.util.ArrayList

object FaqHelper {
    fun generateFaq(): ArrayList<Faq> {
        val arrayFaq: ArrayList<Faq> = ArrayList()
        arrayFaq.add(
            Faq(
                "Apa itu Sispatic?",
                "Sispatic (Sistem Pakar Deteksi Kerusakan Motor Matic) adalah aplikasi yang akan membantu user untuk menemukan kerusakan."
            )
        )
        arrayFaq.add(
            Faq(
                "Apa itu sistem pakar?",
                "Sistem Pakar, atau yang dikenal juga dengan istilah Expert System, adalah salah satu cabang dari kecerdasan buatan yang dirancang untuk meniru keahlian dan pengetahuan manusia di bidang tertentu."
            )
        )
        arrayFaq.add(
            Faq(
                "Bagaimana cara mengidentifikasi kerusakan menggunakan Sispatic ini?",
                "Untuk melakukan identifikasi kerusakan pada motor ikuti langkah berikut:" +
                        "\n1. Pilih menu \"Mulai Identifikasi\"" +
                        "\n2. Pilih jenis gejala sesuai dengan kerusakan yang dialami" +
                        "\n3. Lalu jawab pertanyaan gejala seusai dengan keluhan yang sedang dialami" +
                        "\n4. Setelah selesai sistem akan secara otomatis menampilkan hasil diagnosa kerusakan dan kisaran harga untuk melakukan perbaikannya."
            )
        )
        arrayFaq.add(
            Faq(
                "Bagaimana cara melihat informasi lengkap dari setiap hasil diagnosa kerusakan motor?",
                "Untuk melihat segala informasi lengkap jenis keruskan pada motor ikuti langkah berikut:" +
                        "\n1. Pilih menu \"Informasi Keruskan\"" +
                        "\n2. Pilih jenis kerusakan yang ingin dilihat lalu klik untuk melihat informasi lengkapnya"
            )
        )
        arrayFaq.add(
            Faq(
                "Bagaimana cara melihat informasi pencegahan dari setiap hasil diagnosa kerusakan motor?",
                "Untuk melihat segala informasi lengkap jenis keruskan pada motor ikuti langkah berikut:" +
                        "\n1. Pilih menu \"Informasi Pencegahan\"" +
                        "\n2. Pilih jenis kerusakan yang ingin dilihat lalu klik untuk melihat informasi lengkapnya"
            )
        )
        return arrayFaq
    }
}
