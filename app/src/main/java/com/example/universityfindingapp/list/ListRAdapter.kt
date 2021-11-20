package com.example.universityfindingapp.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.universityfindingapp.databinding.LinearViewGridBinding
import com.example.universityfindingapp.network.UniversityProperty

class ListRAdapter(private val onClickListener: OnClickListener) :ListAdapter<UniversityProperty, ListRAdapter.UniversityPropertyViewHolder>(DiffCallback) {

    class UniversityPropertyViewHolder(private var binding: LinearViewGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(universityProperty: UniversityProperty) {
            binding.property = universityProperty
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<UniversityProperty>() {
        override fun areItemsTheSame(
            oldItem: UniversityProperty,
            newItem: UniversityProperty
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: UniversityProperty,
            newItem: UniversityProperty
        ): Boolean {
            return oldItem.collegeName == newItem.collegeName
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UniversityPropertyViewHolder {
        return UniversityPropertyViewHolder(LinearViewGridBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: UniversityPropertyViewHolder, position: Int) {
        val universityProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(universityProperty)
        }
        holder.bind(universityProperty)
    }
    class OnClickListener(val clickListener: (universityProperty: UniversityProperty) -> Unit) {
        fun onClick(universityProperty: UniversityProperty) = clickListener(universityProperty)
    }

}




