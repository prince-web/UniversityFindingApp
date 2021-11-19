package com.example.universityfindingapp.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.universityfindingapp.R
import com.example.universityfindingapp.databinding.FragmentListBinding


class ListFragment : Fragment() {

    private val viewModel : ListViewModel by lazy{
        ViewModelProvider(this).get(ListViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding:FragmentListBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_list,container,false)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        return binding.root
    }
}