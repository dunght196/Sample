package com.example.notificationsample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.widget.RemoteViews
import android.widget.TextView
import com.example.notificationsample.Constant.CHANNEL_ID
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.notification_small.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()

        btn_show.setOnClickListener {
            var builder = NotificationCompat.Builder(this, Constant.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_app)
                .setContentTitle("My notification")
                .setContentText("Much longer text that cannot fit one line vvcx v vcxvcxvvcxxcvxcvxcvxcvxc")
//                .setStyle(NotificationCompat.BigTextStyle()
//                    .bigText("Much longer text that cannot fit one line vvcx v vcxvcxvvcxxcvxcvxcvxcvxc"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)

            val notificationManager = NotificationManagerCompat.from(this)
            notificationManager.notify(100, builder.build())
        }

        btn_custom.setOnClickListener {
            // Get the layouts to use in the custom notification
            val notificationLayout = RemoteViews(packageName, R.layout.notification_small).apply {
                setTextViewText(R.id.tv_title, "Hello my fench")
            }

            // Apply the layouts to the notification
            var customNotification = NotificationCompat.Builder(this, Constant.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_app)
                .setCustomBigContentView(notificationLayout)
                .build()

            val notificationManager = NotificationManagerCompat.from(this)
            notificationManager.notify(1000, customNotification)
        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
