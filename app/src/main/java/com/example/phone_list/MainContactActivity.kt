package com.example.phone_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.phone_list.databinding.ActivityMainContactBinding

class MainContactActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainContactBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}