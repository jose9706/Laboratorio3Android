package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val button = findViewById<Button>(R.id.button8)
        val button2 = findViewById<Button>(R.id.button9)
        //val settings: SharedPreferences =
          //  getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE)
        //settings.edit().clear().commit()
        button.setOnClickListener(View.OnClickListener { view ->
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        })
        button2.setOnClickListener(View.OnClickListener { view ->
            startActivity(Intent(this, Scores::class.java))
            finish()
        })

    }
}