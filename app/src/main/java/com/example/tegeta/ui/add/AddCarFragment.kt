package com.example.tegeta.ui.add

import android.os.Bundle
import android.text.Editable
import android.text.Spanned
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.tegeta.NavigationInterface
import com.example.tegeta.databinding.AddCarFragmentBinding
import com.example.tegeta.utils.NumberInputReplacementSpan
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import java.util.regex.Pattern


@AndroidEntryPoint
class AddCarFragment : Fragment() {

    companion object {
        fun newInstance() = AddCarFragment()

        private val pattern =
            Pattern.compile("([A-Z]{2}[0-9]{3}[A-Z]{2})", Pattern.CASE_INSENSITIVE)
        private val NUMBER_SPACE_INDICES: IntArray = intArrayOf(2, 5)

    }

    private val viewModel: AddCarViewModel by viewModels()

    private var _binding: AddCarFragmentBinding? = null

    private val binding get() = _binding!!

    private val numberTextChangeListener = object : TextWatcher {
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
            s?.let {
                binding.numberInput.removeTextChangedListener(this)
                binding.numberInput.setText(s.toString().toUpperCase(Locale.ROOT))
                binding.numberInput.setSelection(s.length)
                binding.numberInput.addTextChangedListener(this)
                binding.numberInput.text?.let { it1 -> addNumberSpans(it1, NUMBER_SPACE_INDICES) }
            }
            checkReady()
        }

    }

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

        binding.numberInput.addTextChangedListener(numberTextChangeListener)


        binding.btnEnd.setOnClickListener {
            viewModel.addCar(binding.numberInput.text.toString())
        }

        viewModel.added.observe(viewLifecycleOwner, { added ->
            if (added)
                Navigation.findNavController(binding.root).popBackStack()
        })

    }

    private fun addNumberSpans(editable: Editable, spaceIndices: IntArray) {
        removeSpans(editable)
        for (index in spaceIndices) {
            if (index <= editable.length) {
                editable.setSpan(
                    NumberInputReplacementSpan(replaceString = "-"), index - 1, index,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
    }

    private fun removeSpans(editable: Editable) {
        val toRemoveSpans =
            editable.getSpans(0, editable.length, NumberInputReplacementSpan::class.java)
        for (element in toRemoveSpans) editable.removeSpan(element)
    }

    fun checkReady() {
        binding.btnEnd.isEnabled =
            viewModel.chosenService.isNotEmpty() && pattern.matcher(
                binding.numberInput.text.toString()
            ).matches()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}