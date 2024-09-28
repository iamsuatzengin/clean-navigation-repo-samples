package com.suatzengin.cleannavigation.service

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.suatzengin.cleannavigation.screen.notification

class NotificationsService : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        // Handle new notifications here
        val packageName = sbn.packageName

        var ticker = ""

        sbn.notification.tickerText?.let {
            ticker = it.toString()
        }

        val bundle = sbn.notification.extras

        val title = bundle.getString("android.title")
        val text = bundle.getString("android.text")

        Log.e("packageName", packageName)
        Log.e("title", title.toString())
        Log.e("text", text.toString())
        Log.e("ticker", ticker)


    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        // Handle removed notifications here
    }

}