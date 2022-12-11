package com.example.futax.futax_application.ui.simple

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.futax.R
import com.example.futax.databinding.FragmentSimpleLogsBinding
import com.example.futax.futax_application.ui.adapters.SimpleLogAdapter
import kotlinx.coroutines.launch

class SimpleLogsFragment : Fragment() {
    private var _binding : FragmentSimpleLogsBinding? = null
    private val binding get() = _binding!!
    private val viewModel : SimpleViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSimpleLogsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SimpleLogAdapter()
        binding.rvSimpleLogs.adapter = adapter

        binding.include3.toolbar.apply {
            inflateMenu(R.menu.menu_simple_logs)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_simple_logs_back -> findNavController().popBackStack()

                    R.id.action_simple_logs_clear -> viewModel.clearSimpleLogs()
                }
                true
            }
        }

        lifecycleScope.launch {
            viewModel.getSimpleLogs().collect {
                adapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}