package com.example.apk_sispakenmatic.activities.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apk_sispakenmatic.R
import com.example.apk_sispakenmatic.adapters.HistoryAdapter
import com.example.apk_sispakenmatic.databinding.ActivityHistoryBinding
import com.example.apk_sispakenmatic.objects.History
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlin.math.log

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var adapter: HistoryAdapter
    private lateinit var arrayRiwayat: ArrayList<History>
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            backButton.setOnClickListener {
                onBackPressed()
            }
        }

        getDataFromCloud()

        binding.apply {
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean = false

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    loadIndicator.visibility = View.VISIBLE
                    db.collection("riwayat")
                        .document(arrayRiwayat[viewHolder.adapterPosition].id)
                        .delete()
                        .addOnCompleteListener {taskWrite ->
                            if (taskWrite.isSuccessful) {
                                /*arrayRiwayat.removeAt(viewHolder.adapterPosition)
                                adapter.notifyItemRemoved(viewHolder.adapterPosition)*/
                                loadIndicator.visibility = View.GONE
                                Toast.makeText(
                                    this@HistoryActivity,
                                    "Data Berhasil Dihapus!", Toast.LENGTH_SHORT).show()
                                getDataFromCloud()
                            }
                        }
                }
            }).attachToRecyclerView(historyRv)
        }
    }

    private fun getDataFromCloud() {
        binding.apply {
            loadIndicator.visibility = View.VISIBLE
            db.collection("riwayat")
                .get()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        loadIndicator.visibility = View.GONE
                        arrayRiwayat = ArrayList()
                        for (document in it.result) {
                            arrayRiwayat.add(
                                History(
                                    document.id,
                                    document.data["hasil_name"].toString(),
                                    document.data["kota1"].toString(),
                                    document.data["harga1"].toString().toInt(),
                                    document.data["kota2"].toString(),
                                    document.data["harga2"].toString().toInt()
                                )
                            )
                        }
                        adapter = HistoryAdapter(arrayRiwayat)
                        historyRv.layoutManager = LinearLayoutManager(this@HistoryActivity)
                        historyRv.adapter = adapter
                    }
                }
        }
    }
}