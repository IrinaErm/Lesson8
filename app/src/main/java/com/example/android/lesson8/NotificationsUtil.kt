package com.example.android.lesson8

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class NotificationsUtil {

    companion object {
        const val NOTIF_RESULT_STRING = "notif_result_string"

        fun createLocalNotification(context: Context, contentText: String) {
            val intent = Intent(context, MainActivity::class.java).apply {
                putExtra(NOTIF_RESULT_STRING, context.getString(R.string.notif_tapped))
            }
            val pendingIntent: PendingIntent? = TaskStackBuilder.create(context).run {
                addNextIntentWithParentStack(intent)
                getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
            }

            val builder = NotificationCompat.Builder(context, context.getString(R.string.local_notif_channel_id))
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("A joke")
                .setContentText(contentText)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)

            with(NotificationManagerCompat.from(context)) {
                // notificationId is a unique int for each notification that you must define
                notify(0, builder.build())
            }
        }

        // must create the notification channel before posting any notifications on Android 8.0 and higher
        // should execute this code as soon as the app starts
        fun createLocalNotificationsChannel(context: Context) {
            // VERSION_CODES.O released publicly as Android 8.0 in August 2017.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = context.getString(R.string.local_notif_channel_name)
                val descriptionText = context.getString(R.string.local_notif_channel_desc)
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val mChannel = NotificationChannel(context.getString(R.string.local_notif_channel_id), name, importance).apply {
                    description = descriptionText
                }

                val notificationManager =
                    context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(mChannel)
            }
        }
    }
}