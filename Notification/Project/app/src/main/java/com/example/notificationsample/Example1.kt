package com.example.notificationsample

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_example1.*

class Example1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example1)

        AppUtil.createNotificationChannel(this)
        btn_show.setOnClickListener {
            // Create an Intent for the activity you want to start
            val resultIntent = Intent(this, ResultActivity::class.java)
            // Create the TaskStackBuilder
            val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(this).run {
                // Add the intent, which inflates the back stack
                addNextIntentWithParentStack(resultIntent)
                // Get the PendingIntent containing the entire back stack
                getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
            }

            var builder = NotificationCompat.Builder(this, Constant.CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_icon_background)
                .setContentTitle("My notification")
                .setContentText("Much longer text that cannot fit one line vvcx v vcxvcxvvcxxcvxcvxcvxcvxc")
//                .setStyle(NotificationCompat.BigTextStyle()
//                    .bigText("Much longer text that cannot fit one line vvcx v vcxvcxvvcxxcvxcvxcvxcvxc"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(resultPendingIntent)

            val notificationManager = NotificationManagerCompat.from(this)
            notificationManager.notify(100, builder.build())
        }
    }
}
