package com.example.apk_sispakenmatic.adapters

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.apk_sispakenmatic.R
import com.example.apk_sispakenmatic.databinding.ListitemPusatbantuanBinding
import com.example.apk_sispakenmatic.objects.Faq

class HelpCenterAdapter(val arrayFaq: ArrayList<Faq>) :
    RecyclerView.Adapter<HelpCenterAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ListitemPusatbantuanBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem_pusatbantuan, parent, false)
    )

    override fun getItemCount(): Int = arrayFaq.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val faq = arrayFaq[position]

        with(holder) {
            binding.apply {
                faqQuestion.text = faq.question
                faqAnswer.text = faq.answer

                itemView.setOnClickListener {
                    if (faqAnswer.visibility == View.GONE) {
                        TransitionManager.beginDelayedTransition(mainContainer, AutoTransition())
                        faqAnswer.visibility = View.VISIBLE
                        arrowIcon.rotation = 270F
                    } else {
                        faqAnswer.visibility = View.GONE
                        arrowIcon.rotation = 180F
                        TransitionManager.beginDelayedTransition(mainContainer, AutoTransition())
                    }
                }
            }
        }
    }
}