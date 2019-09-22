package com.allef.developer.marketplaceoffers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.gms.analytics.HitBuilders
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.*
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var myRef2:DatabaseReference
    private var adapter :ArrayAdapter<String>? = null
    var namesCityes = arrayListOf<String>()
    var names = arrayListOf<IndicationCity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//
//        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener(OnCompleteListener { task ->
//                if (!task.isSuccessful) {
//                    Log.w("Allef", "getInstanceId failed", task.exception)
//                    return@OnCompleteListener
//                }
//
//                // Get new Instance ID token
//                val token = task.result?.token
//
//                // Log and toast
//                val msg =  token
//                Log.d("Allef", msg)
//                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
//            })
//        val t = this.application as App
//        val tra = t.getDefaultTracker()
//        tra?.setScreenName("Tela de Main")
//        tra?.send(HitBuilders.ScreenViewBuilder().build())

////        val uid = UUID.randomUUID().toString()


        newcity.setOnClickListener {
            startActivity<NewProductActivity>()
//            val dta = FirebaseDatabase.getInstance()
//            val uid = UUID.randomUUID().toString()
//            val city = IndicationCity(uid,"Ribeirao Preto")
//            val myRef = dta.reference.child("Cidades")
//            myRef.child(uid).setValue(city)
        }
        ll.setOnItemClickListener { parent, view, position, id ->
            val database = FirebaseDatabase.getInstance()
            val myRef2 = database.reference.child("Cidades").child(names[position].id)
            myRef2.removeValue()
        }




    }

    override fun onStart() {
        super.onStart()
        val database = FirebaseDatabase.getInstance()
        myRef2 = database.reference.child("Cidades")


        //lendo os dados
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                names.clear()
                for ( c in dataSnapshot.children){
                    val post = c.getValue(IndicationCity::class.java)
                    post?.let { names.add(it) }

                }

                val adapterListName = names.map{ it.cityName }
                adapter = ArrayAdapter(this@MainActivity,android.R.layout.simple_list_item_1,adapterListName)
                ll.adapter = adapter
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("Allef", "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef2.addValueEventListener(postListener)
    }
}
