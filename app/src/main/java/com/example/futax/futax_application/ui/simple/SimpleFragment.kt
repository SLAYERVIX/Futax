package com.example.futax.futax_application.ui.simple

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.futax.databinding.FragmentSimpleBinding
import com.example.futax.futax_application.domain.models.SimpleCalc
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class SimpleFragment : Fragment() {
    private lateinit var binding: FragmentSimpleBinding
    private val simpleCalc: SimpleCalc = SimpleCalc()
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
        binding.simple = simpleCalc

        binding.btnCalculate.setOnClickListener {
            lifecycleScope.launch {
                if (binding.etSellingPrice.text!!.isNotEmpty() && binding.tietQuantity.text!!.isNotEmpty()) {
                    simpleCalc.setSellingPrice(
                        binding.etSellingPrice.text.toString().toInt()
                    )
                    simpleCalc.setQuantity(
                        binding.tietQuantity.text.toString().toInt()
                    )
                    simpleCalc.calculate()
                } else {
                    Snackbar.make(
                        view,
                        "Please fulfill all required information",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}