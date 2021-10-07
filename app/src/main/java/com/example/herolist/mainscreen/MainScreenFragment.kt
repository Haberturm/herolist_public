package com.example.herolist.mainscreen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.herolist.R
import com.example.herolist.databinding.FragmentMainScreenBinding
import com.example.herolist.databinding.ListItemBinding
import com.example.herolist.network.HeroApiFilter

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
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.heroList.adapter = RvAdapter(RvAdapter.OnClickListener{
            viewModel.displayPropertyDetails(it)
        })
        initDropDown(binding)

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(
                    MainScreenFragmentDirections.actionMainScreenFragmentToBioFragment(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })



        return binding.root
    }

    private fun initDropDown(binding: FragmentMainScreenBinding){
        val items = viewModel.publishers.value
        val adapter = ArrayAdapter(requireContext(),R.layout.drop_down_item, items!!)
        (binding.menu.editText as? AutoCompleteTextView)?.apply {
            setAdapter(adapter)
            setText(items[0],false)
            addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewModel.updateFilter(
                            when(s.toString()){
                                items[1] -> HeroApiFilter.SHOW_DC
                                items[2] -> HeroApiFilter.SHOW_MARVEL
                                items[3] -> HeroApiFilter.SHOW_DARK_HORSE
                                items[4] -> HeroApiFilter.SHOW_OTHER
                                else -> HeroApiFilter.SHOW_ALL
                            }
                    )
                }

                override fun afterTextChanged(s: Editable?) {
                }

            })
        }

  //      (binding.menu.editText as? AutoCompleteTextView)?.
        



    }


}