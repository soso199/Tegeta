package com.example.tegeta.ui.add

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.tegeta.NavigationInterface
import com.example.tegeta.R
import com.example.tegeta.databinding.AddCarFragmentBinding
import com.example.tegeta.databinding.FragmentHistoryBinding

class AddCarFragment : Fragment() {

    companion object {
        fun newInstance() = AddCarFragment()
    }

    private  val viewModel: AddCarViewModel by viewModels()

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

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}