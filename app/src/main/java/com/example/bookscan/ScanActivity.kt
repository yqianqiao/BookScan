package com.example.bookscan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookscan.databinding.ActivityScanBinding

class ScanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}