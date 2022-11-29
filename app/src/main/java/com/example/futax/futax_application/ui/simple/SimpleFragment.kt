package com.example.futax.futax_application.ui.simple

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.futax.databinding.FragmentSimpleBinding
import com.example.futax.futax_application.ui.TaxViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class SimpleFragment : Fragment() {
    private lateinit var binding: FragmentSimpleBinding
    private val viewModel: TaxViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSimpleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.btnCalculate.setOnClickListener {
            lifecycleScope.launch {

                if (binding.etSellingPrice.text!!.isNotEmpty()) {
                    viewModel.sellingPrice.emit(
                        binding.etSellingPrice.text.toString().toInt()
                    )
                } else {
                    Snackbar.make(view, "Selling price cannot be empty.", Snackbar.LENGTH_SHORT).show()
                }


                if (binding.tietQuantity.text!!.isNotEmpty()) {
                    viewModel.quantity.emit(
                        binding.tietQuantity.text.toString().toInt()
                    )
                }
                else {
                    Snackbar.make(view, "Quantity cannot be empty.", Snackbar.LENGTH_SHORT).show()
                }

                viewModel.calculateBtnOnClick()

                launch {
                    viewModel.earning.collect {
                        binding.tvEarning.text = it.toString()
                    }
                }

                launch {
                    viewModel.taxes.collect {
                        binding.tvTaxes.text = it.toString()
                    }
                }
            }
        }
    }
}