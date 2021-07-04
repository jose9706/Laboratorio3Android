package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class FillNewScore : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fill_new_score)
        val button = findViewById<Button>(R.id.button10)
        button.setOnClickListener(View.OnClickListener { view ->
            var editTextHello = findViewById<EditText>(R.id.scoreName)
            var prefs = getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE)
            var scoreToSave = prefs.getInt("scoreToSave", 0) //0 is the default value
            var scoreSpot = prefs.getInt("scoreSpot", 0) //0 is the default value
            if (scoreSpot == 1) {
                val editor: SharedPreferences.Editor = prefs.edit()
                editor.putInt("score1", scoreToSave)
                editor.putString("score1Tag", editTextHello.text.toString())
                editor.commit()
            }
            if (scoreSpot == 2) {
                moveScoresDownList(2)
                val editor: SharedPreferences.Editor = prefs.edit()
                editor.putInt("score2", scoreToSave)
                editor.putString("score2Tag", editTextHello.text.toString())
                editor.commit()
            }
            if (scoreSpot == 3) {
                moveScoresDownList(3)
                val editor: SharedPreferences.Editor = prefs.edit()
                editor.putInt("score3", scoreToSave)
                editor.putString("score3Tag", editTextHello.text.toString())
                editor.commit()
            }
            if (scoreSpot == 4) {
                moveScoresDownList(4)
                val editor: SharedPreferences.Editor = prefs.edit()
                editor.putInt("score4", scoreToSave)
                editor.putString("score4Tag", editTextHello.text.toString())
                editor.commit()
            }
            if (scoreSpot == 5) {
                moveScoresDownList(5)
                val editor: SharedPreferences.Editor = prefs.edit()
                editor.putInt("score5", scoreToSave)
                editor.putString("score5Tag", editTextHello.text.toString())
                editor.commit()
            }
            startActivity(Intent(this, Scores::class.java))
            finish()
        })
    }
    private fun moveScoresDownList(locationStart : Int) {
        var prefs = getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE)
        var scoreToMove5 = prefs.getInt("score5", 0) //0 is the default value
        var tagToMove5 = prefs.getString("score5Tag", "")
        var tagToMove2 = prefs.getString("score2Tag", "")
        var tagToMove3 = prefs.getString("score3Tag", "")
        var tagToMove4 = prefs.getString("score4Tag", "")
        var scoreToMove2 = prefs.getInt("score2", 0)
        var scoreToMove3 = prefs.getInt("score3", 0)
        var scoreToMove4 = prefs.getInt("score4", 0)
        val editor: SharedPreferences.Editor = prefs.edit()
        if(locationStart == 5) {
            editor.putInt("score4", scoreToMove5)
            editor.putString("score4Tag", tagToMove5)

            editor.putInt("score3", scoreToMove4)
            editor.putString("score3Tag", tagToMove4)

            editor.putInt("score2", scoreToMove3)
            editor.putString("score2Tag", tagToMove3)

            editor.putInt("score1", scoreToMove2)
            editor.putString("score1Tag", tagToMove2)

            editor.commit()
        }
        if(locationStart == 4) {
            editor.putInt("score3", scoreToMove4)
            editor.putString("score3Tag", tagToMove4)

            editor.putInt("score2", scoreToMove3)
            editor.putString("score2Tag", tagToMove3)

            editor.putInt("score1", scoreToMove2)
            editor.putString("score1Tag", tagToMove2)

            editor.commit()
        }
        if(locationStart ==3) {
            editor.putInt("score2", scoreToMove3)
            editor.putString("score2Tag", tagToMove3)

            editor.putInt("score1", scoreToMove2)
            editor.putString("score1Tag", tagToMove2)
        }
        if(locationStart == 2){
            editor.putInt("score1", scoreToMove2)
            editor.putString("score1Tag", tagToMove2)
        }
        editor.commit()
    }
}