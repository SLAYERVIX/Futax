package com.example.futax.futax_application.ui.simple

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.futax.R
import com.example.futax.databinding.FragmentSimpleBinding
import com.example.futax.futax_application.ui.adapters.SimpleAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SimpleFragment : Fragment() {
    private var _binding: FragmentSimpleBinding? = null
    private val binding get() = _binding!!
    private val model : SimpleViewModel by viewModels()

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
        binding.model = model

        binding.include2.toolbar.inflateMenu(R.menu.menu_simple)

        val adapter = SimpleAdapter()
        binding.rvSimple.adapter = adapter

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