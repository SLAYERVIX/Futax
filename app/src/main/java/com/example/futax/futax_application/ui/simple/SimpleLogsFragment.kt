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
import com.google.android.material.snackbar.Snackbar
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
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        val adapter = SimpleLogAdapter()
        binding.rvSimpleLogs.adapter = adapter

        lifecycleScope.launch {
            viewModel.getSimpleLogs().collect {
                adapter.submitList(it)
                viewModel.isLogSetter(it.isEmpty())
            }
        }

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
                    val log = adapter.currentList[viewHolder.adapterPosition]
                    viewModel.deleteSimpleLog(log)

                    Snackbar.make(view, "Log Deleted", Snackbar.LENGTH_LONG)
                        .setAction("Undo") {
                            viewModel.insertSimpleLog(log)
                        }.show()
                }
            }
        }).attachToRecyclerView(binding.rvSimpleLogs)

        binding.apply {
            btnGridView.setOnClickListener {
                adapter.isGrid = true
                binding.rvSimpleLogs.layoutManager = GridLayoutManager(view.context, 2)
            }

            btnLinearView.setOnClickListener {
                adapter.isGrid = false
                binding.rvSimpleLogs.layoutManager = LinearLayoutManager(view.context)
            }
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

//    fun deleteItem(simpleLog: SimpleLog) {
//        val builder = AlertDialog.Builder(requireContext())
//        builder.setTitle("Delete Log")
//        builder.setMessage("Are you sure you want to delete this log ?")
//
//        builder.setPositiveButton("Delete") { dialog, _ ->
//            viewModel.deleteSimpleLog(simpleLog)
//            dialog.cancel()
//        }
//
//        builder.setNegativeButton("Cancel") { dialog, _ ->
//            dialog.cancel()
//        }
//        // Create the AlertDialog
//        builder.create().show()
//    }
}