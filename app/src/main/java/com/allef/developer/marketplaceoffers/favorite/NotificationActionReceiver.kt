package com.allef.developer.marketplaceoffers.favorite

import android.app.NotificationChannel
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import android.view.Menu
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.allef.developer.marketplaceoffers.R
import com.allef.developer.marketplaceoffers.menu.MenuProductActivity
import com.allef.developer.marketplaceoffers.splash.SplashActivity
import com.google.firebase.database.collection.LLRBNode
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.media.AudioAttributes
import android.app.Notification
import android.app.NotificationManager
import android.graphics.BitmapFactory
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat


class NotificationActionReceiver: FirebaseMessagingService() {

    override fun onNewToken(s: String?) {
        super.onNewToken(s)
    }

    val channelId = "channel"
    val appname = applicationContext.getString(R.string.character_counter_pattern)

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.notification != null){
            showNotification(remoteMessage.notification?.title, remoteMessage.notification?.body)
        }else {
            showNotification(
                remoteMessage.data["title"],
                remoteMessage.data["body"])
        }

    }
    private fun showNotification(title: String?, body: String?) {
        createChannel(channelId,appname)

        val intent = Intent(this, SplashActivity::class.java).apply {
            putExtra("pushnotification", "yes")
            putExtra("title",title)
            putExtra("body",body)
        }
        intent.action = "number_notification" + count

        val bigTextStyle = NotificationCompat
            .BigTextStyle()
            .bigText(body)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)


        val pendingIntent = PendingIntent.getActivity(this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT)

        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(this,channelId).apply {
            setSmallIcon(R.mipmap.ic_launcher)
            setContentTitle(title)
            setContentText(body)
//            setLargeIcon(
//                BitmapFactory.decodeResource(resources,
//                    R.drawable.noo))
            setAutoCancel(true)
            setChannelId(channelId)
            setSound(soundUri)
            setContentIntent(pendingIntent)
            setDefaults(Notification.DEFAULT_ALL)
            setStyle(bigTextStyle)
            setDefaults(Notification.DEFAULT_SOUND)
            color = ActivityCompat.getColor(applicationContext,R.color.colorAccent)
        }

        val notification = NotificationCompat.Builder(this, channelId).apply {
            setSmallIcon(R.mipmap.ic_launcher)
            setContentTitle(title)
//            setLargeIcon(
//                BitmapFactory.decodeResource(resources,
//                    R.drawable.noo))
            color = ActivityCompat.getColor(applicationContext,R.color.colorAccent)
            setContentText(body)
            priority = NotificationCompat.PRIORITY_DEFAULT
            setDefaults(Notification.DEFAULT_ALL)
            setChannelId(channelId)
            setContentIntent(pendingIntent)
            setAutoCancel(true)
            setStyle(bigTextStyle)
        }
        notification.setDefaults(Notification.DEFAULT_SOUND)



        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationManager = NotificationManagerCompat.from(this)
            notificationManager.areNotificationsEnabled()
            notificationManager.notify(count,notification.build())
        }else{
            val notificationManager = NotificationManagerCompat.from(this)
            notificationManager.areNotificationsEnabled()
            notificationManager.notify(count,notificationBuilder.build())
        }
        count++
    }


    private fun createChannel(channelId:String,title: String){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notre  = NotificationChannel(channelId,title, NotificationManager.IMPORTANCE_HIGH).apply {
                enableLights(true)
                lightColor = Color.RED
                enableVibration(true)
                setShowBadge(true)
                importance = NotificationManager.IMPORTANCE_HIGH
                setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION),null)
            }
            notificationManager.createNotificationChannel(notre)
        }
    }
    companion object {
        var count = 0
    }

}