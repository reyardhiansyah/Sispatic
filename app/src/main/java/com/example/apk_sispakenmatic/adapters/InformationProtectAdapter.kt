package com.example.apk_sispakenmatic.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.apk_sispakenmatic.R
import androidx.recyclerview.widget.RecyclerView
import com.example.apk_sispakenmatic.databinding.ListitemInformpencegahanBinding
import com.example.apk_sispakenmatic.objects.Solusi

class InformationProtectAdapter(private val arrayProtect: ArrayList<Solusi>) :
    RecyclerView.Adapter<InformationProtectAdapter.ViewHolder>() {
    private lateinit var onSolutionItemClickListener: OnSolutionItemClickListener

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ListitemInformpencegahanBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem_informpencegahan, parent, false)
    )

    //sambungan dari line 11
    override fun getItemCount(): Int = arrayProtect.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val protect = arrayProtect[position]

        with(holder) {
            binding.apply {
                judulInformasipencegahan.text = protect.name
                deskripsiInformasipencegahan.text = protect.desc
            }
            itemView.setOnClickListener { onSolutionItemClickListener.onItemClick(protect) }
        }
    }

    fun onItemClickListener(onSolutionItemClickListener: OnSolutionItemClickListener) {
        this.onSolutionItemClickListener = onSolutionItemClickListener
    }

    interface OnSolutionItemClickListener {
        fun onItemClick(solusi: Solusi)
    }
}