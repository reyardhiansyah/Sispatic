package com.example.apk_sispakenmatic.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apk_sispakenmatic.R
import com.example.apk_sispakenmatic.databinding.ListitemInformkerusakanBinding
import com.example.apk_sispakenmatic.objects.Solusi

class InformationDamageAdapter(private val arraySolusi: ArrayList<Solusi>) :
    RecyclerView.Adapter<InformationDamageAdapter.ViewHolder>() {
    private lateinit var onSolutionItemClickListener: OnSolutionItemClickListener

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ListitemInformkerusakanBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem_informkerusakan, parent, false)
    )

    override fun getItemCount(): Int = arraySolusi.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val solusi = arraySolusi[position]

        with(holder) {
            binding.apply {
                judulInformasikerusakan.text = solusi.name
                deskripsiInformasikerusakan.text = solusi.desc
            }

            itemView.setOnClickListener {
                onSolutionItemClickListener.onItemClick(solusi)
            }
        }
    }

    fun onItemClickListener(onSolutionItemClickListener: OnSolutionItemClickListener) {
        this.onSolutionItemClickListener = onSolutionItemClickListener
    }

    interface OnSolutionItemClickListener {
        fun onItemClick(solusi: Solusi)
    }
}