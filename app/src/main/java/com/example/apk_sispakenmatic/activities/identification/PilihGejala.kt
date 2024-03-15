package com.example.apk_sispakenmatic.activities.identification

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apk_sispakenmatic.adapters.GejalaAdapter
import com.example.apk_sispakenmatic.databinding.ActivityPilihGejalaBinding
import com.example.apk_sispakenmatic.helpers.SymptomsHelper
import com.example.apk_sispakenmatic.objects.ArrayGejala

class PilihGejala : AppCompatActivity() {
    private lateinit var binding: ActivityPilihGejalaBinding
    private lateinit var userSympthoms:ArrayList<ArrayGejala>
    private lateinit var mainAdapter: GejalaAdapter

    companion object
    {
        @SuppressLint("StaticFieldLeak")
        var activity: Activity? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPilihGejalaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userSympthoms = SymptomsHelper.generateBagian()

        binding.apply {
            mainAdapter = GejalaAdapter(userSympthoms)
            listview.adapter = mainAdapter
            listview.layoutManager = LinearLayoutManager(this@PilihGejala)

            backButton.setOnClickListener {
                onBackPressed()
            }
        }

         mainAdapter.onItemClickListener(object : GejalaAdapter.OnItemClickListener {
                override fun onItemClick(array: ArrayGejala) {
                    startActivity(
                        Intent(this@PilihGejala, Pertanyaan::class.java)
                            .also {
                                it.putExtra("GEJALA", array)
                            }
                    )
                }
            })
    }

    override fun onDestroy()
    {
        super.onDestroy()
        activity = null
    }

    override fun onResume()
    {
        super.onResume()
        activity = this
    }
}