package com.allef.developer.marketplaceoffers

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.developer.allef.sweetmessage.SweetAlertDialog
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_new_product.*
import java.util.*
import android.graphics.Color.parseColor



class NewProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_product)
        val database = FirebaseDatabase.getInstance()

        floatingSaveCity.setOnClickListener {
            if(!edtCity.text?.isEmpty()!!){
                val uid = UUID.randomUUID().toString()
                val ci = IndicationCity(uid,edtCity.text.toString())
                val databaseReference:DatabaseReference = database.reference.child("Cidades")
                databaseReference.child(uid).setValue(ci)
                edtCity.text?.clear()
                val pDialog = SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                pDialog.progressHelper.barColor = Color.parseColor("#A5DC86")
                pDialog.titleText = "Cidade Salva com sucesso."
                pDialog.setCancelable(false)
                pDialog.show()
                pDialog.setConfirmClickListener {
                    finish()
                }
            }
        }



    }
}
