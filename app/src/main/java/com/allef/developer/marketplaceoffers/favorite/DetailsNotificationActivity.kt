package com.allef.developer.marketplaceoffers.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.allef.developer.marketplaceoffers.R
import com.allef.developer.marketplaceoffers.menu.MenuProductActivity
import com.allef.developer.marketplaceoffers.model.CompanyUser
import com.allef.developer.marketplaceoffers.model.emails
import kotlinx.android.synthetic.main.activity_details_notification.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.startActivity

class DetailsNotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_notification)
        val aa = intent.getStringExtra("title")
        val body = intent.getStringExtra("body")
        textView2.text = aa
        textView3.text = body
    }

    override fun onBackPressed() {
        startActivity(intentFor<MenuProductActivity>().clearTop().singleTop())
        finish()

//        super.onBackPressed()
    }

    companion object {
        const val EXTRA_MESSAGE = "message"
    }
}
