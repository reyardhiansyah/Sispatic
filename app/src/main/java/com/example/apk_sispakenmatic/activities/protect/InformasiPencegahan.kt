package com.example.apk_sispakenmatic.activities.protect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apk_sispakenmatic.activities.DetailActivity
import com.example.apk_sispakenmatic.adapters.InformationProtectAdapter
import com.example.apk_sispakenmatic.databinding.ActivityInformasiPencegahanBinding
import com.example.apk_sispakenmatic.objects.Solusi
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class InformasiPencegahan : AppCompatActivity() {
    private lateinit var binding: ActivityInformasiPencegahanBinding
    private lateinit var adapter: InformationProtectAdapter
    private lateinit var arrayPencegahan: ArrayList<Solusi>
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformasiPencegahanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getDataFromCloud()
    }

    private fun getDataFromCloud() {
        binding.apply {
            db.collection("solusi")
                .get()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        arrayPencegahan = ArrayList()
                        for (document in it.result) {
                            arrayPencegahan.add(
                                Solusi(
                                    document.data["header"].toString(),
                                    document.data["desc_crash"].toString(),
                                    document.data["desc_protect"].toString(),
                                    document.data["solution"].toString()
                                )
                            )
                        }
                        adapter = InformationProtectAdapter(arrayPencegahan)
                        rvInformasipencegahan.layoutManager =
                            LinearLayoutManager(this@InformasiPencegahan)
                        rvInformasipencegahan.adapter = adapter
                        adapter.onItemClickListener(object : InformationProtectAdapter.OnSolutionItemClickListener{
                            override fun onItemClick(solusi: Solusi) {
                                startActivity(
                                    Intent(
                                        this@InformasiPencegahan,
                                        DetailActivity::class.java
                                    ).also { intent ->
                                        with(intent){
                                            putExtra("SOLUSI", solusi)
                                            putExtra("TYPE", "pencegahan")
                                        }
                                    }
                                )
                            }

                        })
                    }
                }
                .addOnFailureListener {
                    it.printStackTrace()
                    Log.e("Error", it.message.toString())
                }
            backButton.setOnClickListener {
                onBackPressed()
            }
        }
    }
}