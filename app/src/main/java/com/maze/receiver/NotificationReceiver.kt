package com.maze.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.maze.main.MainActivity
import com.maze.main.SettingsFragment
import com.maze.notification.NotificationHandler


class NotificationReceiver : BroadcastReceiver() {
    private val TAG = "NotificationReceiver"
    override fun onReceive(context: Context, intent: Intent) {
        val message = intent.getStringExtra("Violation Button")

        // Work around for Parking signal //
        Log.i(TAG,"NotificationReceiver")
        val notificationHandler = NotificationHandler(context)
        Log.i(TAG,"showNotification")
        notificationHandler
            .createNotification(
                MainActivity.notificationLabel, MainActivity.notificationDescription
            )

    when (intent.action) {
             NotificationHandler.INTENT_ACTION -> SettingsFragment.newInstance("p","u")
        }

    }
}