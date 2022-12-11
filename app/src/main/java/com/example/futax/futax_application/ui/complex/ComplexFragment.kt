package com.example.futax.futax_application.ui.complex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.futax.R
import com.example.futax.databinding.FragmentComplexBinding
import com.example.futax.futax_application.ui.adapters.ComplexAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ComplexFragment : Fragment() {
    private var _binding: FragmentComplexBinding? = null
    private val binding get() = _binding!!
    private val model: ComplexViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentComplexBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.model = model

        binding.include.toolbar.inflateMenu(R.menu.menu_complex)

        binding.include.toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.action_complex_logs) {
                Navigation.findNavController(view)
                    .navigate(R.id.action_complexFragment2_to_complexLogsFragment2)
            }
            true
        }

        val adapter = ComplexAdapter()
        binding.rvComplex.adapter = adapter

        lifecycleScope.launch(Dispatchers.Main) {
            model.list.collect {
                adapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}