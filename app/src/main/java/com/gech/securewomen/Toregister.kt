package com.gech.securewomen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Toregister : AppCompatActivity() {

    lateinit var editTextName: EditText
    lateinit var editTextNumone : EditText
    lateinit var editTextNumtwo : EditText
    lateinit var editTextNumthree : EditText
    lateinit var editTextNumfour : EditText
    lateinit var editTextNumfive : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toregister)

        editTextName = findViewById(R.id.editTextName)
        editTextNumone = findViewById(R.id.editTextNumone)
        editTextNumtwo = findViewById(R.id.editTextNumtwo)
        editTextNumthree = findViewById(R.id.editTextNumThree)
        editTextNumfour = findViewById(R.id.editTextNumfour)
        editTextNumfive = findViewById(R.id.editTextNumfive)

        retrieveData()

        findViewById<Button>(R.id.button).setOnClickListener {
            saveData()
        }

    }
    private fun retrieveData(){
        val mypref = getSharedPreferences("mypref", MODE_PRIVATE)

        val name = mypref.getString("name","")
        val num1 = mypref.getString("num1","")
        val num2 = mypref.getString("num2","")
        val num3 = mypref.getString("num3","")
        val num4 = mypref.getString("num4","")
        val num5 = mypref.getString("num5","")


        editTextName.setText(name)
        editTextNumone.setText(num1)
        editTextNumtwo.setText(num2)
        editTextNumthree.setText(num3)
        editTextNumfour.setText(num4)
        editTextNumfive.setText(num5)
    }

    private fun saveData() {

        if (editTextName.text.isEmpty()){
            editTextName.error = "Please Enter Name"
            return
        }

        if(editTextNumone.text.isEmpty()){
            editTextNumone.error = "Please Enter Num One"
            return
        }
        if(editTextNumtwo.text.isEmpty()){
            editTextNumtwo.error = "Please Enter Num Two"
            return
        }
        if(editTextNumthree.text.isEmpty()){
            editTextNumthree.error = "Please Enter Num Three"
            return
        }
        if(editTextNumfour.text.isEmpty()){
            editTextNumfour.error = "Please Enter Num Four"
            return
        }
        if(editTextNumfive.text.isEmpty()){
            editTextNumfive.error = "Please Enter Num Five"
            return
        }

        val mypref = getSharedPreferences("mypref", MODE_PRIVATE)
        val editor = mypref.edit()

        editor.putString("name",editTextName.text.toString())
        editor.putString("num1",editTextNumone.text.toString())
        editor.putString("num2",editTextNumtwo.text.toString())
        editor.putString("num3",editTextNumthree.text.toString())
        editor.putString("num4",editTextNumfour.text.toString())
        editor.putString("num5",editTextNumfive.text.toString())
        editor.apply()

        Toast.makeText(this,"Data Saved", Toast.LENGTH_LONG).show()
    }
}