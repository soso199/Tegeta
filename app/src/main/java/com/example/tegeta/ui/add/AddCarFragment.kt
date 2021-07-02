package com.example.tegeta.ui.add

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tegeta.NavigationInterface
import com.example.tegeta.databinding.AddCarFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCarFragment : Fragment() {

    companion object {
        fun newInstance() = AddCarFragment()
    }

    private val viewModel: AddCarViewModel by viewModels()

    private var _binding: AddCarFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddCarFragmentBinding.inflate(inflater, container, false)

        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as? NavigationInterface)?.hideAddFab()
        viewModel.services.observe(viewLifecycleOwner) { services ->
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                services.map { it.name })
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.servicesSpinner.adapter = adapter

            binding.servicesSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        viewModel.chosenService = services[position].name
                        checkReady()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }

                }
        }

        binding.numberInput.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    checkReady()
                }

            }
        )

        binding.btnEnd.setOnClickListener {
            viewModel.addCar(binding.numberInput.text.toString())
        }

    }

    fun checkReady() {
        binding.btnEnd.isEnabled =
            viewModel.chosenService.isNotEmpty() && binding.numberInput.text.toString().isNotEmpty()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}