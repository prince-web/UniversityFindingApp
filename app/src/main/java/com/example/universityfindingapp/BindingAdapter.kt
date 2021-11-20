package com.example.universityfindingapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.universityfindingapp.list.ListRAdapter
import com.example.universityfindingapp.list.UniversityApiStatus
import com.example.universityfindingapp.network.UniversityProperty

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,data:List<UniversityProperty>?){
    val adapter = recyclerView.adapter as ListRAdapter
    adapter.submitList(data)
}

@BindingAdapter("collegeName")
fun bindCollegeList(textView: TextView,collegeName:String?){
    collegeName?.let {
        textView.text = "$collegeName"
    }
}
@BindingAdapter("websites")
fun bindWebsiteList(textView: TextView,website:Array<String>){
    for (i in website){
        textView.text = "$i"
    }
}

@BindingAdapter("domains")
fun bindDomainList(textView: TextView,domain:Array<String>){
    for (i in domain){
        textView.text = "$i"
    }
}




@BindingAdapter("universityApiStatus")
fun bindStatus(statusImageView:ImageView,status:UniversityApiStatus?){
    when(status){
        UniversityApiStatus.LOADING->{
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        UniversityApiStatus.ERROR->{
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_baseline_cloud_off_24)
        }
        UniversityApiStatus.DONE->{
            statusImageView.visibility = View.GONE
        }

    }
}