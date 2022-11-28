package com.example.futax

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.futax.databinding.ActivityMainBinding
import com.example.futax.futax_application.ui.TaxViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: TaxViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.btnCalculate.setOnClickListener {
            lifecycleScope.launch {
                val sellingPrice : Int = binding.tietValue.text.toString().toInt()
                viewModel.sellingPrice.emit(sellingPrice)

                viewModel.calculateEarning()

                viewModel.earning.collect {
                    binding.tvEarning.text = it.toString()
                }
            }
        }
    }
}