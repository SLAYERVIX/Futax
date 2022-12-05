package com.example.futax.futax_application.ui.simple

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.futax.databinding.FragmentSimpleBinding
import com.example.futax.futax_application.domain.models.SimpleCalc
import com.example.futax.futax_application.ui.TaxViewModel
import com.example.futax.futax_application.ui.adapters.SimpleAdapter
import kotlinx.coroutines.launch

class SimpleFragment : Fragment() {
    private var _binding: FragmentSimpleBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TaxViewModel by activityViewModels()
    private val simpleCalc: SimpleCalc = SimpleCalc()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSimpleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.simple = simpleCalc

        val adapter = SimpleAdapter()
        binding.rvSimple.adapter = adapter

        adapter.submitList(simpleCalc.list.value)

        binding.btnCalculate.setOnClickListener {
            lifecycleScope.launch {
                if (binding.etSellingPrice.text!!.isNotEmpty() || binding.tietQuantity.text!!.isNotEmpty()) {

                    simpleCalc.setSellingPrice(binding.etSellingPrice.text.toString().toInt())

                    simpleCalc.setQuantity(binding.tietQuantity.text.toString().toInt())

                    simpleCalc.calculate()
                    adapter.submitList(simpleCalc.list.value)
                    Log.d("Listvvv", simpleCalc.list.value.toString())
                    Log.d("dataxxx", simpleCalc.sellingPrice.value.toString())
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}