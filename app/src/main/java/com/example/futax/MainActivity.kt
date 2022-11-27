package com.example.futax

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.futax.databinding.ActivityMainBinding
import com.example.futax.ui.TaxViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: TaxViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.btnCalculate.setOnClickListener {
            val x : Int = binding.tietValue.text.toString().toInt()
            viewModel.calculateEarning(x)
        }
    }
}