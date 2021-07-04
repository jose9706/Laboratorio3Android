package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class Scores : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scores)
        val button = findViewById<Button>(R.id.button11)

        var prefs = getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE)
        val highScoreText = findViewById<TextView>(R.id.textView7)
        val highScoreText2 = findViewById<TextView>(R.id.textView6)
        val highScoreText3 = findViewById<TextView>(R.id.textView5)
        val highScoreText4 = findViewById<TextView>(R.id.textView4)
        val highScoreText5 = findViewById<TextView>(R.id.textView3)

        var score = prefs.getInt("score1", 0) //0 is the default value
        var scoreTag = prefs.getString("score1Tag", "")
        highScoreText.text = ("Tag: $scoreTag Score: $score").toString()

        score = prefs.getInt("score2", 0) //0 is the default value
        scoreTag = prefs.getString("score2Tag", "")
        highScoreText2.text = ("Tag: $scoreTag Score: $score").toString()

        score = prefs.getInt("score3", 0) //0 is the default value
        scoreTag = prefs.getString("score3Tag", "")
        highScoreText3.text = ("Tag: $scoreTag Score: $score").toString()

        score = prefs.getInt("score4", 0) //0 is the default value
        scoreTag = prefs.getString("score4Tag", "")
        highScoreText4.text =("Tag: $scoreTag Score: $score").toString()

        score = prefs.getInt("score5", 0) //0 is the default value
        scoreTag = prefs.getString("score5Tag", "")
        highScoreText5.text = ("Tag: $scoreTag Score: $score").toString()
        button.setOnClickListener(View.OnClickListener { view ->
            startActivity(Intent(this, HomeActivity::class.java))
            finish()

        })

    }
}