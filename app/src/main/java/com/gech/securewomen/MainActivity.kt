package com.gech.securewomen

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {

    lateinit var editTextName: EditText
    lateinit var editTextNumone : EditText
    lateinit var editTextNumtwo : EditText
    lateinit var editTextNumthree : EditText
    lateinit var editTextNumfour : EditText
    lateinit var editTextNumfive : EditText

    lateinit var TextViewname: TextView
    lateinit var TextViewnumone : TextView
    lateinit var TextViewnumtwo : TextView
    lateinit var TextViewnumthree : TextView
    lateinit var TextViewnumfour : TextView
    lateinit var TextViewnumfive : TextView

    private lateinit var textview:TextView
    private lateinit var textview2:TextView
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mypref = getSharedPreferences("mypref", MODE_PRIVATE)

        val name = mypref.getString("name",null)
        if (name == null){
            setContentView(R.layout.activity_main)

            Log.i("MainActivity","App was opened !")
            Toast.makeText(this,"Don't Be Scared You are safe now 1234565 !!!".plus(name),Toast.LENGTH_LONG).show()


            findViewById<Button>(R.id.buttontoregisterpage).setOnClickListener {
                val intent = Intent(this, Toregister::class.java)
                startActivity(intent)
            }
        }else{

            setContentView(R.layout.activity_afterregister)


            Log.i("AfterRegisterActivity","App was opened !")
            Toast.makeText(this,"Don't Be Scared You are safe now !!!!".plus(name),Toast.LENGTH_LONG).show()

            textview = findViewById(R.id.tvGpsLatitude)
            textview2 = findViewById(R.id.tvGpsLongitude)
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

            val mypref = getSharedPreferences("mypref", MODE_PRIVATE)

            val name = mypref.getString("name","")
            val num1 = mypref.getString("num1","")
            val num2 = mypref.getString("num2","")
            val num3 = mypref.getString("num3","")
            val num4 = mypref.getString("num4","")
            val num5 = mypref.getString("num5","")


            TextViewname = findViewById(R.id.textViewname)
            TextViewnumone = findViewById(R.id.textViewnumone)
            TextViewnumtwo = findViewById(R.id.textViewnumtwo)
            TextViewnumthree = findViewById(R.id.textViewnumthree)
            TextViewnumfour = findViewById(R.id.textViewnumfour)
            TextViewnumfive = findViewById(R.id.textViewnumfive)


            TextViewname.setText(name)
            TextViewnumone.setText(num1)
            TextViewnumtwo.setText(num2)
            TextViewnumthree.setText(num3)
            TextViewnumfour.setText(num4)
            TextViewnumfive.setText(num5)

            checkPermissions()

            findViewById<Button>(R.id.editdetails).setOnClickListener {
                val intent = Intent(this, EditDetails::class.java)
                startActivity(intent)
            }

        }

    }



    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.SEND_SMS),1)
        }else{
            getLastLocation()
        }
    }

    private fun getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            if (it == null){
                Toast.makeText(this,"Sorry Can't get Location",Toast.LENGTH_LONG).show()
            }
            else it.apply {
                val latitude = it.latitude
                val longitude = it.longitude
                textview.text = "Latitude : $latitude"
                textview2.text = "Longitude : $longitude"

                val mypref = getSharedPreferences("mypref", MODE_PRIVATE)

                val num1 = mypref.getString("num1","")
                val num2 = mypref.getString("num2","")
                val num3 = mypref.getString("num3","")
                val num4 = mypref.getString("num4","")
                val num5 = mypref.getString("num5","")

                var obj=SmsManager.getDefault()

                obj.sendTextMessage("$num1",
                    null,"http://maps.google.com/maps?q=$latitude,$longitude",null,null)
                /*obj.sendTextMessage("$num2",
                    null,"$latitude $longitude",null,null)
                obj.sendTextMessage("$num3",
                    null,"$latitude $longitude",null,null)
                obj.sendTextMessage("$num4",
                    null,"$latitude $longitude",null,null)
                obj.sendTextMessage("$num5",
                    null,"$latitude $longitude",null,null)*/

            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 1){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"PERMMISSION GRANTED",Toast.LENGTH_LONG).show()
                    getLastLocation()
                }
                else{
                    Toast.makeText(this,"PERMISSION DENIED",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}