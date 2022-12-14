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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.futax.R
import com.example.futax.databinding.FragmentComplexLogsBinding
import com.example.futax.futax_application.data.local.models.ComplexLog
import com.example.futax.futax_application.ui.adapters.ComplexLogAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ComplexLogsFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentComplexLogsBinding
    private val viewModel: ComplexViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentComplexLogsBinding.inflate(inflater, container, false)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ComplexLogAdapter()
        binding.rvComplexLogs.adapter = adapter

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                lifecycleScope.launch {
                    viewModel.deleteComplexLog(adapter.currentList[viewHolder.adapterPosition])
                }
            }

        }).attachToRecyclerView(binding.rvComplexLogs)

        lifecycleScope.launch {
            viewModel.getComplexLogs().collect {
                adapter.submitList(it)

                if (it.isEmpty()) {
                    binding.apply {
                        textView2.visibility = View.VISIBLE
                        btnGridView.visibility = View.GONE
                        btnLinearView.visibility = View.GONE
                    }
                } else {
                    binding.apply {
                        textView2.visibility = View.GONE
                        btnGridView.visibility = View.VISIBLE
                        btnLinearView.visibility = View.VISIBLE
                    }
                }
            }
        }

        binding.btnGridView.setOnClickListener {
            binding.rvComplexLogs.layoutManager = GridLayoutManager(view.context, 2)
        }

        binding.btnLinearView.setOnClickListener {
            binding.rvComplexLogs.layoutManager = LinearLayoutManager(view.context)
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_complex_logs, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
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