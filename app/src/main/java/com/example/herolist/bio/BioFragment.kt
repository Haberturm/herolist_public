package com.example.herolist.bio

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.herolist.R
import com.example.herolist.databinding.FragmentBioBinding

class BioFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentBioBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val heroProperty = BioFragmentArgs.fromBundle(requireArguments()).selectedProperty
        val viewModelFactory = BioViewModelFactory(heroProperty,application)
        binding.viewModel = ViewModelProvider(this, viewModelFactory).get(BioViewModel::class.java)


        return binding.root
    }


}