package com.example.apk_sispakenmatic.activities.identification

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.apk_sispakenmatic.databinding.ActivityPertanyaanBinding
import com.example.apk_sispakenmatic.objects.ArrayGejala

class Pertanyaan : AppCompatActivity() {
    private lateinit var binding: ActivityPertanyaanBinding
    private lateinit var gejala: ArrayGejala
    private lateinit var hasil: String
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPertanyaanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gejala = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("GEJALA", ArrayGejala::class.java)!!
        } else {
            intent.getParcelableExtra("GEJALA")!!
        }

        binding.apply {
            nameDetail.text = gejala.arrays[0].pertanyaan

            buttonYa.setOnClickListener {
                when (position) {
                    0 -> {
                        loadingBar.visibility = View.VISIBLE
                        questionContainer.visibility = View.GONE
                        buttonContainer.visibility = View.GONE
                        Handler(Looper.getMainLooper()).postDelayed({
                            loadingBar.visibility = View.GONE
                            questionContainer.visibility = View.VISIBLE
                            buttonContainer.visibility = View.VISIBLE
                            gejala.arrays[0].isTrue = true
                            position += 1
                            nameDetail.text = gejala.arrays[1].pertanyaan
                        }, 1000)
                    }

                    1 -> {
                        loadingBar.visibility = View.VISIBLE
                        questionContainer.visibility = View.GONE
                        buttonContainer.visibility = View.GONE
                        Handler(Looper.getMainLooper()).postDelayed({
                            loadingBar.visibility = View.GONE
                            questionContainer.visibility = View.VISIBLE
                            buttonContainer.visibility = View.VISIBLE
                            gejala.arrays[1].isTrue = true
                            position += 1
                            nameDetail.text = gejala.arrays[2].pertanyaan
                        }, 1000)
                    }

                    2 -> {
                        gejala.arrays[2].isTrue = true
                        checkSymptoms()
                        toSolution(hasil)
                    }
                }
            }

            buttonTidak.setOnClickListener {
                when (position) {
                    0 -> {
                        loadingBar.visibility = View.VISIBLE
                        questionContainer.visibility = View.GONE
                        buttonContainer.visibility = View.GONE
                        Handler(Looper.getMainLooper()).postDelayed({
                            loadingBar.visibility = View.GONE
                            questionContainer.visibility = View.VISIBLE
                            buttonContainer.visibility = View.VISIBLE
                            gejala.arrays[0].isTrue = false
                            position += 1
                            nameDetail.text = gejala.arrays[1].pertanyaan
                        }, 1000)
                    }

                    1 -> {
                        loadingBar.visibility = View.VISIBLE
                        questionContainer.visibility = View.GONE
                        buttonContainer.visibility = View.GONE
                        Handler(Looper.getMainLooper()).postDelayed({
                            loadingBar.visibility = View.GONE
                            questionContainer.visibility = View.VISIBLE
                            buttonContainer.visibility = View.VISIBLE
                            gejala.arrays[1].isTrue = false
                            position += 1
                            nameDetail.text = gejala.arrays[2].pertanyaan
                        }, 1000)
                    }

                    2 -> {
                        gejala.arrays[2].isTrue = false
                        checkSymptoms()
                        toSolution(hasil)
                    }
                }
            }
        }
    }

    private fun toSolution(hasilSolusi: String) {
        startActivity(
            Intent(this@Pertanyaan, HasilSolusi::class.java)
                .also {
                    it.putExtra("HASIL", hasilSolusi)
                }
        )
        finish()
    }

    private fun checkSymptoms() {
        if (gejala.gejala == "Mesin Tidak Mau Hidup") {
            if (!gejala.arrays[0].isTrue && !gejala.arrays[1].isTrue && gejala.arrays[2].isTrue) {
                hasil = "Kerusakan pada Busi"
            }
            if (gejala.arrays[0].isTrue && !gejala.arrays[1].isTrue && !gejala.arrays[2].isTrue) {
                hasil = "Kerusakan pada Kleb"
            }
            if (gejala.arrays[0].isTrue && !gejala.arrays[1].isTrue && gejala.arrays[2].isTrue) {
                hasil = "Kerusakan pada Ignition Coil"
            }
            if (!gejala.arrays[0].isTrue && gejala.arrays[1].isTrue && gejala.arrays[2].isTrue) {
                hasil = "Kerusakan pada CDI"
            }
            if (!gejala.arrays[0].isTrue && !gejala.arrays[1].isTrue && !gejala.arrays[2].isTrue) {
                hasil = "Motor Elit kok Bensin Sulit"
            }
        }
    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        AlertDialog.Builder(this).also {
            with(it) {
                setMessage("Apakah yakin ingin kembali?")
                setTitle("Peringatan!")
                setCancelable(false)
                setPositiveButton("Ya") { _, _ ->
                    finish()
                }

                setNegativeButton("Tidak") { dialog, _ ->
                    dialog.cancel()
                }
                create().show()
            }
        }
    }
}