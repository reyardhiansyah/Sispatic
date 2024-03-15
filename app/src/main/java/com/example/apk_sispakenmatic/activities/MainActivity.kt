package com.example.apk_sispakenmatic.activities

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.apk_sispakenmatic.activities.aboutapp.AboutActivity
import com.example.apk_sispakenmatic.activities.identification.PilihGejala
import com.example.apk_sispakenmatic.activities.crash.InformasiKerusakan
import com.example.apk_sispakenmatic.activities.history.HistoryActivity
import com.example.apk_sispakenmatic.activities.protect.InformasiPencegahan
import com.example.apk_sispakenmatic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnIdengejala.setOnClickListener {
                startActivity(
                    Intent(
                        this@MainActivity, PilihGejala::class.java
                    )
                )
            }

            btnInforkerusakan.setOnClickListener {
                startActivity(
                    Intent(
                        this@MainActivity, InformasiKerusakan::class.java
                    )
                )
            }

            btnInforpencegahan.setOnClickListener {
                startActivity(
                    Intent(
                        this@MainActivity, InformasiPencegahan::class.java
                    )
                )
            }

            btnRiwayatDiagnosa.setOnClickListener {
                startActivity(
                    Intent(
                        this@MainActivity, HistoryActivity::class.java
                    )
                )
            }

            btnMap.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q=Bengkel")
                    ).also {
                        it.setPackage("com.google.android.apps.maps")
                    }
                )
            }

            btnTentangaplikasi.setOnClickListener {
                startActivity(
                    Intent(
                        this@MainActivity, AboutActivity::class.java
                    )
                )
            }
        }
    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Apakah yakin ingin keluar dari Sispatic?")
        builder.setTitle("Peringatan!")
        builder.setCancelable(false)
        builder.setPositiveButton("Ya") { dialog, which ->
            finish()
        }

        builder.setNegativeButton("Tidak") { dialog, which ->
            dialog.cancel()
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }
}