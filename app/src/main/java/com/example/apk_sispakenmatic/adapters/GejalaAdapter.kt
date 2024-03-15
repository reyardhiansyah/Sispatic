package com.example.apk_sispakenmatic.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apk_sispakenmatic.R
import com.example.apk_sispakenmatic.databinding.ListitemGejalaBinding
import com.example.apk_sispakenmatic.objects.ArrayGejala

class GejalaAdapter(private val arrayList: ArrayList<ArrayGejala>) :
    RecyclerView.Adapter<GejalaAdapter.ViewHolder>() {
    private lateinit var onItemClickListener: OnItemClickListener

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ListitemGejalaBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.listitem_gejala, parent, false
        )
    )

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gejala = arrayList[position]

        holder.apply {
            binding.apply {
                personalItem.text = gejala.gejala
            }

            itemView.setOnClickListener {
                onItemClickListener.onItemClick(gejala)
            }
        }
    }

    fun onItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(array: ArrayGejala)
    }
}