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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.futax.R
import com.example.futax.databinding.FragmentSimpleLogsBinding
import com.example.futax.futax_application.ui.adapters.SimpleLogAdapter
import kotlinx.coroutines.launch

class SimpleLogsFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentSimpleLogsBinding
    private val viewModel: SimpleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSimpleLogsBinding.inflate(inflater, container, false)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SimpleLogAdapter()
        binding.rvSimpleLogs.adapter = adapter

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
                    viewModel.deleteSimpleLog(adapter.currentList[viewHolder.adapterPosition])
                }
            }

        }).attachToRecyclerView(binding.rvSimpleLogs)

        lifecycleScope.launch {
            viewModel.getSimpleLogs().collect {
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
            adapter.isGrid = true
            binding.rvSimpleLogs.layoutManager = GridLayoutManager(view.context, 2)
        }

        binding.btnLinearView.setOnClickListener {
            adapter.isGrid = false
            binding.rvSimpleLogs.layoutManager = LinearLayoutManager(view.context)
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_simple_logs, menu)
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