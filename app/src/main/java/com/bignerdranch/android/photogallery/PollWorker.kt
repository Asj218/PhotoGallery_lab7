package com.bignerdranch.android.photogallery

import android.app.PendingIntent
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.WorkManager // Добавлено
import androidx.work.PeriodicWorkRequest // Добавлено
import androidx.work.Constraints // Добавлено
import androidx.work.NetworkType // Добавлено
import androidx.work.ExistingPeriodicWorkPolicy // Добавлено
import java.util.concurrent.TimeUnit // Добавлено

private const val TAG = "PollWorker"

class PollWorker(val context: Context, workerParams: WorkerParameters): Worker(context, workerParams) {

    override fun doWork(): Result {

        val query = QueryPreferences.getStoredQuery(context)
        val lastResultId = QueryPreferences.getLastResultId(context)
        val items: List<GalleryItem> = if (query.isEmpty()) {
            FlickrFetchr().fetchPhotosRequest()
                .execute()
                .body()
                ?.photos
                ?.galleryItems ?: emptyList()//
        } else {
            FlickrFetchr().searchPhotosRequest(
                query)
                .execute()
                .body()
                ?.photos
                ?.galleryItems ?: emptyList()//
        } ?: emptyList()

        if (items.isEmpty()) {
            return Result.success()
        }

        val resultId = items.first().id
        if (resultId == lastResultId) {
            Log.i(TAG, "Got an old result:$resultId")
        } else {
            Log.i(TAG, "Got a new result: $resultId")
            QueryPreferences.setLastResultId(context, resultId)

            val intent = PhotoGalleryActivity.newIntent(context)
            //val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0) ????????? вместо последующей строки
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            val resources = context.resources
            val notification = NotificationCompat
                    .Builder(context, NOTIFICATION_CHANNEL_ID)
                    .setTicker(resources.getString(R.string.new_pictures_title))
                    .setSmallIcon(android.R.drawable.ic_menu_report_image)
                    .setContentTitle(resources.getString(R.string.new_pictures_title))
                    .setContentText(resources.getString(R.string.new_pictures_text))
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build()
            val notificationManager = NotificationManagerCompat.from(context)
            notificationManager.notify(0, notification)
        }

        return Result.success()
    }
}