package com.example.apk_sispakenmatic.activities.identification

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.apk_sispakenmatic.activities.MainActivity
import com.example.apk_sispakenmatic.databinding.ActivityHasilSolusiBinding
import com.example.apk_sispakenmatic.objects.DaftarHarga
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class HasilSolusi : AppCompatActivity() {
    private lateinit var binding: ActivityHasilSolusiBinding
    private lateinit var arrayHarga: ArrayList<DaftarHarga>
    private var solusi: String = ""
    private val db = Firebase.firestore

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHasilSolusiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        solusi = intent.getStringExtra("HASIL")!!

        binding.apply {
            val hasil = SpannableString(solusi)
            hasil.setSpan(
                StyleSpan(Typeface.BOLD),
                0,
                hasil.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            hasilDetail.text = "Hasil yang didapatkan dari diagnosa adalah\n $hasil"

            getDataFromCloud(hasil.toString()) // Kerusakan pada Ignition Coil

            btnIdentifikasiUlang.setOnClickListener {
                finish()
            }

            btnSelesai.setOnClickListener {
                if (PilihGejala.activity != null) {
                    finish()
                    PilihGejala.activity?.finish()
                }
            }
        }
    }

    private fun getDataFromCloud(solusi: String) {
        binding.apply {
            dataLoadIndicator.visibility = View.VISIBLE
            db.collection("solusi")
                .get()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        for (document in it.result) {
                            if (solusi.lowercase() == document.id.lowercase()) {
                                db.collection("solusi")
                                    .document(document.id)
                                    .collection("price_list")
                                    .get()
                                    .addOnCompleteListener { res ->
                                        if (res.isSuccessful) {
                                            arrayHarga = ArrayList()
                                            for (kota in res.result) {
                                                arrayHarga.add(
                                                    DaftarHarga(
                                                        kota.data["city"].toString(),
                                                        kota.data["price"].toString().toInt()
                                                    )
                                                )
                                            }
                                            dataLoadIndicator.visibility = View.GONE

                                            // Array index -> 0, 1, 2, 3, 4, 5,..
                                            kota1.text = arrayHarga[0].name
                                            kota2.text = arrayHarga[1].name
                                            harga1.text = "Rp ${arrayHarga[0].harga}"
                                            harga2.text = "Rp ${arrayHarga[1].harga}"
                                            //write history
                                            val history = hashMapOf(
                                                "hasil_name" to document.data["header"].toString(),
                                                "kota1" to arrayHarga[0].name,
                                                "kota2" to arrayHarga[1].name,
                                                "harga1" to arrayHarga[0].harga,
                                                "harga2" to arrayHarga[1].harga
                                            )

                                            db.collection("riwayat")
                                                .add(history)
                                                .addOnCompleteListener { taskWrite ->
                                                    if (taskWrite.isSuccessful) {
                                                        Log.e("CODE: 200", "Data write success!")
                                                        Toast.makeText(
                                                            this@HasilSolusi,
                                                            "Riwayat ditambahkan!",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                    } else {
                                                        Log.e("CODE: 404", "Data write failed!")
                                                        Toast.makeText(
                                                            this@HasilSolusi,
                                                            "Riwayat gagal ditambahkan.",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                    }
                                                }
                                        }
                                    }
                            }
                        }
                    }
                }
        }
    }
}