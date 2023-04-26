package com.vyapp.beguess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.vyapp.beguess.presentation.BeGuessViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: BeGuessViewModel by lazy {
        ViewModelProvider(this)[BeGuessViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}