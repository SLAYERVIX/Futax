package com.example.futax.futax_application.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.futax.databinding.FragmentHomeBinding
import com.example.futax.futax_application.ui.adapters.ComplexLogAdapter
import com.example.futax.futax_application.ui.adapters.SimpleLogAdapter
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val simpleAdapter = SimpleLogAdapter()
        binding.rvRecentSimple.adapter = simpleAdapter

        val complexAdapter = ComplexLogAdapter()
        binding.rvRecentComplex.adapter = complexAdapter

        lifecycleScope.launch {
            launch {
                viewModel.getSimpleLogs().collect {
                    if (it.size <= 10) {
                        simpleAdapter.submitList(it.take(it.size))
                    } else {
                        simpleAdapter.submitList(it.take(10))
                    }
                }
            }

            launch {
                viewModel.getComplexLogs().collect {
                    if (it.size <= 10) {
                        complexAdapter.submitList(it.take(it.size))
                    } else {
                        complexAdapter.submitList(it.take(10))
                    }
                }
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }
}