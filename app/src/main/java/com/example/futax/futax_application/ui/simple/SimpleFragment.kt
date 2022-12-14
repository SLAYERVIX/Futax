package com.example.futax.futax_application.ui.simple

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.futax.R
import com.example.futax.databinding.FragmentSimpleBinding
import com.example.futax.futax_application.ui.adapters.SimpleAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SimpleFragment : Fragment(), MenuProvider {
    private lateinit var binding: FragmentSimpleBinding
    private val model: SimpleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSimpleBinding.inflate(inflater, container, false)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.model = model

        val adapter = SimpleAdapter()
        binding.rvSimple.adapter = adapter

        binding.btnSimpleExpand.setOnClickListener {
            if (binding.rvSimple.visibility == View.GONE) {
                binding.apply {
                    rvSimple.visibility = View.VISIBLE
                    divider.visibility = View.VISIBLE
                    btnSimpleExpand.setImageResource(R.drawable.ic_baseline_expand_less_24)
                }
            } else {
                binding.apply {
                    rvSimple.visibility = View.GONE
                    divider.visibility = View.GONE
                    btnSimpleExpand.setImageResource(R.drawable.ic_baseline_expand_more_24)
                }
            }
        }

        lifecycleScope.launch(Dispatchers.Main) {
            model.list.collect {
                adapter.submitList(it)
            }
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_simple, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.action_simple_logs -> {
                findNavController().navigate(R.id.action_simpleFragment2_to_simpleLogsFragment)
                true
            }

            else -> false
        }
    }
}