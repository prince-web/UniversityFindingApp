package com.example.universityfindingapp.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.universityfindingapp.R
import com.example.universityfindingapp.databinding.FragmentListBinding
import com.example.universityfindingapp.network.UniversityApiFilter


class ListFragment : Fragment() {

    private val viewModel : ListViewModel by lazy{
        ViewModelProvider(this).get(ListViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentListBinding.inflate(inflater)
        //val binding:FragmentListBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_list,container,false)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.listOfColleges.adapter = ListRAdapter(ListRAdapter.OnClickListener{
            viewModel.displayPropertyDetails(it)
        })

        viewModel.navigationToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if(null != it){
                this.findNavController().navigate(ListFragmentDirections.actionShowDetail(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.updateFilter(
            when (item.itemId) {
                R.id.show_India_menu -> UniversityApiFilter.SHOW_INDIA
                R.id.show_australia_menu -> UniversityApiFilter.SHOW_AUSTRALIA
                else ->UniversityApiFilter.SHOW_INDIA

            }
        )
        return true
    }
}