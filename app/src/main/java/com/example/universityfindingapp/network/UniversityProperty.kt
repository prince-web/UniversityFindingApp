package com.example.universityfindingapp.network

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UniversityProperty (
   //@Json(name = "state-province")val state_province:String,
    val country:String,
    //used to map name from the JSON to collegeName in our class
    @Json(name = "name") val collegeName :String):Parcelable
