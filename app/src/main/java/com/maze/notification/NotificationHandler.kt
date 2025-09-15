package com.maze.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.maze.R
import com.maze.main.MainActivity


class NotificationHandler(mContext: Context) {
    private val TAG = "NotificationHandler"

    private val NOTIFICATION_ID = 1002;

    lateinit var mNotificationManager: NotificationManager

    var mContext: Context? = mContext

    var mChannelId: String = mContext.getString(R.string.notification_channel_id)

    init {
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        Log.d(TAG, "createNotificationChannel: $mChannelId")
        val channelName = mContext?.getString(R.string.notification_channel_name)
        val channel =
            NotificationChannel(mChannelId, channelName, NotificationManager.IMPORTANCE_HIGH)
        channel.description = mContext?.getString(R.string.notification_channel_description)
        mNotificationManager =
            mContext?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.createNotificationChannel(channel)
    }

    fun createNotification(title: String, description: String) {
        Log.d(TAG, "createNotification")

        if (mContext != null) {
            val learnMoreIntent = Intent(mContext, MainActivity::class.java)
            learnMoreIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            learnMoreIntent.putExtra("learnMore", true)
            val learnMorePendingIntent =
                PendingIntent.getActivity(
                    mContext,
                    1,
                    learnMoreIntent,
                    PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
                )

            val findParkingIntent = Intent(mContext, MainActivity::class.java)
            findParkingIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            findParkingIntent.putExtra("findParking", true)
            val findParkingPendingIntent =
                PendingIntent.getActivity(
                    mContext,
                    2,
                    findParkingIntent,
                    PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
                )

            val notification = NotificationCompat.Builder(
                mContext!!.applicationContext, mChannelId
            )
                .setSmallIcon(R.drawable.maze_logo)
                .setContentTitle(title)
                .setContentText(description)
                .setCategory(Notification.CATEGORY_CALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .addAction(0, INTENT_ACTION, learnMorePendingIntent)
                .addAction(0, FIND_PARK, findParkingPendingIntent)

            val notificationManagerCompact =
                NotificationManagerCompat.from(mContext!!.applicationContext)

            // Check if notifications are enabled
            if (notificationManagerCompact.areNotificationsEnabled()) {
                try {
                    notificationManagerCompact.notify(NOTIFICATION_ID, notification.build())
                } catch (e: SecurityException) {
                    Log.e(TAG, "Notification permission not granted: ${e.message}")
                }
            } else {
                Log.e(TAG, "Notifications are disabled or permission not granted.")
            }
        }
    }

    companion object {
        const val INTENT_ACTION = "Learn More"
        const val FIND_PARK = "Find Parking"
        const val NOTIFICATION_KEY = "Violation Button"
    }

}