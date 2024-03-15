package com.example.apk_sispakenmatic.activities.aboutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apk_sispakenmatic.R
import com.example.apk_sispakenmatic.adapters.HelpCenterAdapter
import com.example.apk_sispakenmatic.databinding.ActivityHelpcenterBinding
import com.example.apk_sispakenmatic.databinding.ListitemPusatbantuanBinding
import com.example.apk_sispakenmatic.helpers.FaqHelper
import com.example.apk_sispakenmatic.objects.Faq

class HelpCenterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHelpcenterBinding
    private lateinit var adapter: HelpCenterAdapter
    private lateinit var arrayFaq: ArrayList<Faq>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelpcenterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            arrayFaq = FaqHelper.generateFaq()

            adapter = HelpCenterAdapter(arrayFaq)
            rvHelpCenter.adapter = adapter
            rvHelpCenter.layoutManager = LinearLayoutManager(this@HelpCenterActivity)

            backButton.setOnClickListener {
                onBackPressed()
            }
        }
    }
}