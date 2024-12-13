package com.bignerdranch.android.photogallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class PhotoGalleryViewModel : ViewModel() {
    val galleryItemLiveData: LiveData<List<GalleryItem>>
    init {
        galleryItemLiveData = FlickrFetchr().searchPhotos("planets")
//897 Создайте
//XML-файл
//res/menu/fragment_photo_gallery.xml
//меню
// для
// PhotoGallery Fragment (если забыли, как что делается,
// вернитесь к главе 14).
    }
}