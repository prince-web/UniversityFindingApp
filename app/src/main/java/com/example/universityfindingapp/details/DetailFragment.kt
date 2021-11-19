package com.example.universityfindingapp.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.universityfindingapp.R
import com.example.universityfindingapp.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val application = requireNotNull(activity).application
        val binding :FragmentDetailBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_detail,container,false)
        binding.lifecycleOwner = this


        return binding.root
    }

}