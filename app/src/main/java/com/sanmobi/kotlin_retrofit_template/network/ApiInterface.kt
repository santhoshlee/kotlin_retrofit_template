package com.sanmobi.kotlin_retrofit_template.network


import com.sanmobi.kotlin_retrofit_template.model.Photo
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("/photos")
    fun getPhotos() : Call<List<Photo>>

}