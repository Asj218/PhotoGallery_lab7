package com.bignerdranch.android.photogallery.api

import retrofit2.Call
import retrofit2.http.GET

//class FlickrApi {
    interface FlickrApi {
    @GET(
        "services/rest/?method=flickr.interestingness.getList" +
            "&api_key=0b743369651c56a204f787508fbdc6e9" +
                "&format=json" +
                "&nojsoncallback=1" +
                "&extras=url_s"
    )
    fun fetchPhotos(): Call<FlickrResponse>
    }
//}