package com.example.futax.futax_application.ui.complex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.futax.R
import com.example.futax.databinding.FragmentComplexLogsBinding
import com.example.futax.futax_application.ui.adapters.ComplexLogAdapter
import kotlinx.coroutines.launch

class ComplexLogsFragment : Fragment() {

    private var _binding : FragmentComplexLogsBinding? = null
    private val binding get() = _binding!!
    private val viewModel : ComplexViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentComplexLogsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ComplexLogAdapter()
        binding.rvComplexLogs.adapter = adapter

        binding.include3.toolbar.apply {
            inflateMenu(R.menu.menu_complex_logs)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_complex_logs_back -> findNavController().popBackStack()

                    R.id.action_clear_complex_logs -> viewModel.clearComplexLogs()
                }
                true
            }
        }

        lifecycleScope.launch {
            viewModel.getComplexLogs().collect {
                adapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}