package com.gech.securewomen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var editTextName: EditText
    lateinit var editTextNumone : EditText
    lateinit var editTextNumtwo : EditText
    lateinit var editTextNumthree : EditText
    lateinit var editTextNumfour : EditText
    lateinit var editTextNumfive : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mypref = getSharedPreferences("mypref", MODE_PRIVATE)

        val name = mypref.getString("name",null)
        if (name == null){
            setContentView(R.layout.activity_main)

            Log.i("MainActivity","App was opened !")
            Toast.makeText(this,"Don't Be Scared You are safe now !!!".plus(name),Toast.LENGTH_LONG).show()


            findViewById<Button>(R.id.buttontoregisterpage).setOnClickListener {
                val intent = Intent(this, Toregister::class.java)
                startActivity(intent)
            }
        }else{
            setContentView(R.layout.activity_afterregister)

            Log.i("AfterRegisterActivity","App was opened !")
            Toast.makeText(this,"Don't Be Scared You are safe now !!!!".plus(name),Toast.LENGTH_LONG).show()
        }

    }


}