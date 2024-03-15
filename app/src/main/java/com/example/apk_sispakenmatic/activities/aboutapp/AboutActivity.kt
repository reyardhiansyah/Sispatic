package com.example.apk_sispakenmatic.activities.aboutapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apk_sispakenmatic.R
import com.example.apk_sispakenmatic.databinding.ActivityAboutBinding
import com.example.apk_sispakenmatic.dialogs.ContactDeveloperBottomSheet

class AboutActivity : AppCompatActivity(), ContactDeveloperBottomSheet.OnContactDevButtonClicked {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            buttonBantuan.setOnClickListener {
                startActivity(
                    Intent(
                        this@AboutActivity,
                        HelpCenterActivity::class.java
                    )
                )
            }

            buttonHubDev.setOnClickListener {
                val dialog = ContactDeveloperBottomSheet(this@AboutActivity)

                if (dialog.window != null)
                    dialog.show()

                dialog.onContactDevButtonClicked(this@AboutActivity)
            }

            backButton.setOnClickListener {
                onBackPressed()
            }
        }
    }

    override fun contactMailClicked() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // Only email apps handle this.
            putExtra(Intent.EXTRA_EMAIL, arrayOf("rawsstander@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, "Kritik & Saran SISPATIC")
            putExtra(Intent.EXTRA_TEXT, ContactDeveloperBottomSheet.MESSAGES_MAIL)
        }

        if (intent.resolveActivity(packageManager) != null)
            startActivity(intent)
    }

    override fun contactWhatsAppClicked() {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("${ContactDeveloperBottomSheet.WA_BASE_URL}${ContactDeveloperBottomSheet.MESSAGES}")
            )
        )
    }
}