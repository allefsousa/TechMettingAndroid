package com.allef.developer.marketplaceoffers.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.allef.developer.marketplaceoffers.menu.MenuProductActivity
import com.allef.developer.marketplaceoffers.R
import com.allef.developer.marketplaceoffers.favorite.DetailsNotificationActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import org.jetbrains.anko.startActivity



class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //Token do usuario
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(SplashActivity::class.java.simpleName, "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }
                val token = task.result?.token
                Log.d(SplashActivity::class.java.simpleName, "getInstanceId Token $token")


            })

        val b = intent.extras
        val someData = b?.getString("pushnotification")
        Log.d(MenuProductActivity::class.java.simpleName, "getInstanceId Token $someData")

        if (someData != null){
            val it = Intent(this@SplashActivity,DetailsNotificationActivity::class.java)
            val title= b.getString("title")
            val body = b.getString("body")
            it.putExtra("title",title)
            it.putExtra("body",body)
            startActivity(it)
            finish()
        }else{
            Handler().postDelayed({
                startActivity<MenuProductActivity>()
                finish()
            }, 1500)
        }

    }
    companion object {
        const val EXTRA_MESSAGE = "message"
    }
}
