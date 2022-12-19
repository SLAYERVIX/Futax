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
import com.example.futax.databinding.FragmentComplexBinding
import com.example.futax.futax_application.ui.adapters.ComplexAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ComplexFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentComplexBinding
    private val model: ComplexViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentComplexBinding.inflate(inflater, container, false)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.model = model

        binding.btnComplexExpand.setOnClickListener {
            if (binding.rvComplex.visibility == View.GONE) {
                binding.apply {
                    rvComplex.visibility = View.VISIBLE
                    btnComplexExpand.setImageResource(R.drawable.ic_baseline_expand_less_24)
                }
            } else {
                binding.apply {
                    rvComplex.visibility = View.GONE
                    btnComplexExpand.setImageResource(R.drawable.ic_baseline_expand_more_24)
                }
            }
        }


        val adapter = ComplexAdapter()
        binding.rvComplex.adapter = adapter

        lifecycleScope.launch(Dispatchers.Main) {
            model.list.collect {
                adapter.submitList(it)
            }
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_complex, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.action_complex_logs -> {
                findNavController().navigate(R.id.action_complexFragment2_to_complexLogsFragment2)
                true
            }
            R.id.action_complex_reset -> {
                model.resetFields()
                true
            }
            else -> false
        }
    }
}