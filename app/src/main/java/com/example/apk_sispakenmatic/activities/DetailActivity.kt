package com.example.apk_sispakenmatic.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.apk_sispakenmatic.databinding.ActivityDetailBinding
import com.example.apk_sispakenmatic.objects.Solusi

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var solusi: Solusi
    private lateinit var type: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            solusi = intent.getParcelableExtra("SOLUSI")!!
            type = intent.getStringExtra("TYPE")!!

            backButton.setOnClickListener {
                onBackPressed()
            }

            Log.e("Solusi", solusi.toString())

            if (type == "kerusakan") {
                headerText.text = solusi.name
                descMitigation.text = solusi.desc!!.replace("\\n", "\n")
                solusionText.text = solusi.solution!!.replace("\\n", "\n")
            } else {
                headerText.text = solusi.name
                descMitigation.text = solusi.mitigation!!.replace("\\n", "\n")
                solusionTitle.visibility = View.GONE
                solusionText.visibility = View.GONE
            }
        }
    }
}