package com.example.universityfindingapp.network

import com.squareup.moshi.Json

class UniversityProperty (
   // @Json(name = "state-province")val state_province:String,
    val country:String,
    @Json(name = "name") val collegeName :String,
    )