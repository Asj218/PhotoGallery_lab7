package com.bignerdranch.android.photogallery.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url
import retrofit2.http.Query

//class FlickrApi {
    interface FlickrApi {
        //"&api_key=0b743369651c56a204f787508fbdc6e9" +
    @GET("services/rest?method=flickr.interestingness.getList")

    fun fetchPhotos(): Call<FlickrResponse>

    @GET
    fun fetchUrlBytes(@Url url: String): Call<ResponseBody>

    @GET("services/rest?method=flickr.photos.search")
        fun searchPhotos(@Query("text") query: String): Call<FlickrResponse>
    }
//}