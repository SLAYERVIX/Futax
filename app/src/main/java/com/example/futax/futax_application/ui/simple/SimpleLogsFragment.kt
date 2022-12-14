package com.example.futax.futax_application.ui.simple

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
import com.example.futax.databinding.FragmentSimpleLogsBinding
import com.example.futax.futax_application.ui.adapters.SimpleLogAdapter
import kotlinx.coroutines.launch

class SimpleLogsFragment : Fragment(),MenuProvider {

    private lateinit var binding : FragmentSimpleLogsBinding
    private val viewModel : SimpleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSimpleLogsBinding.inflate(inflater, container, false)
        val menuHost : MenuHost = requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner,Lifecycle.State.RESUMED)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SimpleLogAdapter()
        binding.rvSimpleLogs.adapter = adapter

        lifecycleScope.launch {
            viewModel.getSimpleLogs().collect {
                adapter.submitList(it)
            }
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_simple_logs,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
       return when (menuItem.itemId) {
            R.id.action_simple_logs_back -> {
                findNavController().popBackStack()
                true
            }
           R.id.action_simple_logs_clear -> {
               viewModel.clearSimpleLogs()
               true
           }
            else -> false
        }
    }
}