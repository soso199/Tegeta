package com.example.tegeta.ui.home

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tegeta.NavigationInterface
import com.example.tegeta.R
import com.example.tegeta.data.model.CurrentCar
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

        adapter = HomeCarsAdapter(::showAmountDialog)
        binding.recycler.layoutManager = LinearLayoutManager(context)
        binding.recycler.adapter = adapter

        homeViewModel.cars.observe(viewLifecycleOwner) { cars ->
            adapter.updateData(cars)
        }
    }

    private fun showAmountDialog(car: CurrentCar) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.amount_dialog_title))
        val input = EditText(context)
        input.setRawInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_SIGNED or InputType.TYPE_NUMBER_FLAG_DECIMAL)

        builder.setView(input)
        builder.setPositiveButton(
            getString(R.string.amount_dialog_finish)
        ) { dialog, _ ->
            dialog.cancel()
            try {
                input.text.toString().toDouble().let {
                    if (it > 0) {
                        car.amount = it
                        homeViewModel.endCurrentCar(car)
                    }
                }
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            }
        }
        builder.setNegativeButton(
            getString(R.string.dialog_cancel)
        ) { dialog, _ -> dialog.cancel() }
        builder.create().apply {
            this.setOnShowListener {
                this.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(resources.getColor(R.color.yellow))
            }
        }.show()
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.getCars()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}