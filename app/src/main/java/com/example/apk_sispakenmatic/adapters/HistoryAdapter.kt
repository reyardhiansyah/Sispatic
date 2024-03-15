package com.example.apk_sispakenmatic.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apk_sispakenmatic.R
import com.example.apk_sispakenmatic.databinding.ListitemHistoryBinding
import com.example.apk_sispakenmatic.objects.History

class HistoryAdapter(val arrayHistory: ArrayList<History>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ListitemHistoryBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem_history, parent, false)
    )

    override fun getItemCount(): Int = arrayHistory.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val history = arrayHistory[position]

        with(holder) {
            binding.apply {
                headerText.text = "${position+1}. ${history.name}"
                kota1.text = history.kota1
                kota2.text = history.kota2
                harga1.text = history.harga1.toString()
                harga2.text = history.harga2.toString()
            }
        }
    }
}