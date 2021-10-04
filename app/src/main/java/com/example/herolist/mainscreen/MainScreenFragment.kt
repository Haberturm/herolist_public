package com.example.herolist.mainscreen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.herolist.R
import com.example.herolist.databinding.FragmentMainScreenBinding
import com.example.herolist.databinding.ListItemBinding

class MainScreenFragment : Fragment() {

    companion object {
        fun newInstance() = MainScreenFragment()
    }


    private val viewModel: MainScreenViewModel by lazy {
        ViewModelProvider(this).get(MainScreenViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainScreenBinding.inflate(inflater)
        //val binding = ListItemBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.heroList.adapter = RvAdapter()
        return binding.root
    }



}