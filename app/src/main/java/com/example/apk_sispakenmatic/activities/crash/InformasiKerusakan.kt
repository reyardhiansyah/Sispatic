package com.example.apk_sispakenmatic.activities.crash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apk_sispakenmatic.activities.DetailActivity
import com.example.apk_sispakenmatic.adapters.InformationDamageAdapter
import com.example.apk_sispakenmatic.databinding.ActivityInformasiKerusakanBinding
import com.example.apk_sispakenmatic.objects.Solusi
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class InformasiKerusakan : AppCompatActivity() {
    private lateinit var binding: ActivityInformasiKerusakanBinding
    private lateinit var adapter: InformationDamageAdapter
    private lateinit var arraySolusi: ArrayList<Solusi>
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformasiKerusakanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataFromCloud()
    }

    private fun getDataFromCloud() {
        binding.apply {
            loadIndicator.visibility = View.VISIBLE
            rvInformasikerusakan.visibility = View.GONE
            db.collection("solusi")
                .get()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        loadIndicator.visibility = View.GONE
                        rvInformasikerusakan.visibility = View.VISIBLE
                        arraySolusi = ArrayList()
                        for (document in it.result) {
                            arraySolusi.add(
                                Solusi(
                                    document.data["header"].toString(),
                                    document.data["desc_crash"].toString(),
                                    document.data["desc_protect"].toString(),
                                    document.data["solution"].toString()
                                )
                            )
                        }
                        adapter = InformationDamageAdapter(arraySolusi)
                        rvInformasikerusakan.layoutManager =
                            LinearLayoutManager(this@InformasiKerusakan)
                        rvInformasikerusakan.adapter = adapter
                        adapter.onItemClickListener(object :
                            InformationDamageAdapter.OnSolutionItemClickListener {
                            override fun onItemClick(solusi: Solusi) {
                                startActivity(
                                    Intent(
                                        this@InformasiKerusakan,
                                        DetailActivity::class.java
                                    ).also { intent ->
                                        with(intent) {
                                            putExtra("SOLUSI", solusi)
                                            putExtra("TYPE", "kerusakan")
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