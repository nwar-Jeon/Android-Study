package com.nwar.individual.aac_viewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.nwar.individual.aac_viewmodel.databinding.FirstFragmentBinding

class FirstFragment() : androidx.fragment.app.Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FirstFragmentBinding>(inflater, R.layout.first_fragment,container, false)
        val factory = ViewModelFactory()
        val viewModel = ViewModelProviders.of(requireActivity(),factory)[CustomViewModel::class.java]
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}