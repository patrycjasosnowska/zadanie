package com.example.zadanie.details

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.zadanie.R
import com.example.zadanie.databinding.ActivityDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    val detailsViewModel: DetailsViewModel by viewModel()

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        val url = intent.getStringExtra(getString(R.string.intent_key_uri))
        if (url != null && url.isNotEmpty()) {
            binding.detailsWebview.loadUrl(url)
        } else {
            Toast.makeText(this, "Unexpected problem with loading a website.", Toast.LENGTH_SHORT)
                .show()
        }
    }
}