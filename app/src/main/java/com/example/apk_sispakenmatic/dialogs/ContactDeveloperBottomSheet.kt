package com.example.apk_sispakenmatic.dialogs

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.example.apk_sispakenmatic.databinding.BottomSheetContactDeveloperBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class ContactDeveloperBottomSheet(context: Context) : BottomSheetDialog(context) {
    /*
    * Nama XML nya -> bottom_sheet_contact_developer.xml
    * Nama Class Bindingnya, ganti simbol _ menjadi huruf besar -> BottomSheetContactDeveloper */
    private lateinit var binding: BottomSheetContactDeveloperBinding
    private lateinit var onContactDevButtonClicked: OnContactDevButtonClicked

    companion object {
        const val WA_BASE_URL = "https://wa.me/6288228024513?text="
        const val MESSAGES =
            "Saya%20ingin%20menyampaikan%20saran%20atau%20kritik%20untuk%20aplikasi%20SISPATIC: %0A"
        const val MESSAGES_MAIL =
            "Saya ingin menyampaikan saran atau kritik untuk aplikasi SISPATIC: \n"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BottomSheetContactDeveloperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCancelable(true)

        binding.apply {
            contactMailButton.setOnClickListener {
                onContactDevButtonClicked.contactMailClicked()
            }

            contactWhatsAppButton.setOnClickListener {
                onContactDevButtonClicked.contactWhatsAppClicked()
            }
        }
    }

    fun onContactDevButtonClicked(onContactDevButtonClicked: OnContactDevButtonClicked) {
        this.onContactDevButtonClicked = onContactDevButtonClicked
    }

    interface OnContactDevButtonClicked {
        fun contactMailClicked()

        fun contactWhatsAppClicked()
    }
}