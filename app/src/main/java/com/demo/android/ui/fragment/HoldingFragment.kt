package com.demo.android.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.demo.android.databinding.FragmentHoldingBinding
import com.demo.android.ui.adapter.HoldingAdapter
import com.demo.android.viewmodel.MainViewModel

class HoldingFragment : Fragment() {
    private var _binding: FragmentHoldingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    private var holdingAdapter: HoldingAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHoldingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            if (state.isLoading) {
                // binding.progressBar.visibility = View.VISIBLE
            }

            state.data?.data?.userHolding?.let {
                holdingAdapter = HoldingAdapter(requireActivity(), it)
                binding.rvHolding.adapter = holdingAdapter
                Log.e("jbgivcfrx", it.toString())
            }

            state.error?.let {
                //binding.textViewResult.text = state.error
                //binding.progressBar.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}