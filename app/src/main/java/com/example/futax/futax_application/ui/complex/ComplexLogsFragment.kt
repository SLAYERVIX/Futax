package com.example.futax.futax_application.ui.complex

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.futax.R
import com.example.futax.databinding.FragmentComplexLogsBinding
import com.example.futax.futax_application.ui.adapters.ComplexLogAdapter
import kotlinx.coroutines.launch

class ComplexLogsFragment : Fragment(),MenuProvider {

    private var _binding : FragmentComplexLogsBinding? = null
    private val binding get() = _binding!!
    private val viewModel : ComplexViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentComplexLogsBinding.inflate(inflater, container, false)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adapter = ComplexLogAdapter()
        binding.rvComplexLogs.adapter = adapter

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

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_complex_logs,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId) {
            R.id.action_complex_logs_back -> {
                findNavController().popBackStack()
                true
            }
            R.id.action_clear_complex_logs -> {
                viewModel.clearComplexLogs()
                true
            }
            else -> false
        }
    }
}