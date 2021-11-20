package com.example.universityfindingapp.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "http://universities.hipolabs.com/"
enum class UniversityApiFilter(val value:String){SHOW_INDIA("India"),SHOW_AUSTRALIA("Australia")}
//to create a Moshi Builder
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//this will create retrofit object
private val retrofit = Retrofit.Builder()
 /*   .addConverterFactory(ScalarsConverterFactory.create())*/
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL).build()



interface UniversityApiService {
    @GET("search")
//call is used to start the object
    fun getProperties(@Query("country")type:String):
            Deferred<List<UniversityProperty>>
}

object UniversityApi {
    val retrofitService: UniversityApiService by lazy {
        retrofit.create(UniversityApiService::class.java)
    }
}