package com.example.tegeta.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tegeta.NavigationInterface
import com.example.tegeta.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: HomeCarsAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as? NavigationInterface)?.showAddFab()

        adapter = HomeCarsAdapter()
        binding.recycler.layoutManager = LinearLayoutManager(context)
        binding.recycler.adapter = adapter

        homeViewModel.cars.observe(viewLifecycleOwner) { cars ->
            adapter.updateData(cars)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}