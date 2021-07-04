package com.example.myapplication


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.preference.PreferenceManager.*
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.concurrent.schedule
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val buttonInit = findViewById<Button>(R.id.button7)
        val highScoreText = findViewById<TextView>(R.id.textView2)
        button.isClickable = false
        button2.isClickable = false
        button3.isClickable = false
        button4.isClickable = false
        button5.isClickable = false
        button6.isClickable = false
        fun disableAllButtons() {
            button.isClickable = false
            button2.isClickable = false
            button3.isClickable = false
            button4.isClickable = false
            button5.isClickable = false
            button6.isClickable = false
            buttonInit.isClickable = false
        }
        fun enableAllButtons() {
            button.isClickable = true
            button2.isClickable = true
            button3.isClickable = true
            button4.isClickable = true
            button5.isClickable = true
            button6.isClickable = true
            buttonInit.isClickable = true
        }
        val soundList: MutableList<Int> = ArrayList()
        soundList.add(R.raw.do2)
        soundList.add(R.raw.mi)
        soundList.add(R.raw.re)
        soundList.add(R.raw.si)
        soundList.add(R.raw.sol)
        soundList.add(R.raw.mistretched)
        soundList.add(R.raw.dostretched)
        var listOfButtons: MutableList<Int> = ArrayList()
        var sequenceDone : Boolean = false
        var counter : Int = 0
        var sequenceLength : Int = 1
        var userListOfButtons : MutableList<Int> = ArrayList()
        fun resetEverything(restartSequence : Boolean = false) {
            counter = -1
            sequenceDone = false
            listOfButtons.clear()
            userListOfButtons.clear()
            if(restartSequence) {
                sequenceLength = 1
            }
        }
        fun checkProgressAndRestartGame(counter : Int) {
            if(listOfButtons.isNotEmpty() and userListOfButtons.isNotEmpty()) {
                if(listOfButtons[counter] != userListOfButtons[counter]) {
                    Toast.makeText(this@MainActivity, "You failed the sequence", Toast.LENGTH_SHORT)
                        .show()
                    checkScore(scoreToCheck = sequenceLength-1)
                    resetEverything(true)
                    buttonInit.performClick()
                } else {
                    if(counter >= sequenceLength - 1) {
                        Toast.makeText(this@MainActivity, "Success!!", Toast.LENGTH_SHORT).show()
                        var stringRecord = "HighScore: $sequenceLength"
                        sequenceLength += 1
                        highScoreText.text = stringRecord
                        resetEverything()
                        buttonInit.performClick()
                    }
                }
            }
        }

        buttonInit.setOnClickListener(View.OnClickListener { view ->
            disableAllButtons()
            for(i in 1..sequenceLength) {
                val buttonToPress: Int = Random.nextInt(6)
                listOfButtons.add(buttonToPress)
                if(buttonToPress == 0) {
                    displayNumber(buttonToPress, button, 0, i, soundList)
                }
                if(buttonToPress == 1) {
                    displayNumber(buttonToPress, button2, 1, i, soundList)
                }
                if(buttonToPress == 2) {
                    displayNumber(buttonToPress, button3, 2, i, soundList)
                }
                if(buttonToPress == 3) {
                    displayNumber(buttonToPress, button4, 3, i, soundList)
                }
                if(buttonToPress == 4) {
                    displayNumber(buttonToPress, button5, 4, i, soundList)
                }
                if(buttonToPress == 5) {
                    displayNumber(buttonToPress, button6, 5, i, soundList)
                }
                Timer().schedule((2000*sequenceLength).toLong()) {
                    enableAllButtons()
                    sequenceDone = true
                }
            }
        })
        button.setOnClickListener(View.OnClickListener { view ->
            if(sequenceDone) {
                userListOfButtons.add(0)
                checkProgressAndRestartGame(counter)
                counter += 1
            }
        })
        button2.setOnClickListener(View.OnClickListener { view ->
            if(sequenceDone) {
                userListOfButtons.add(1)
                checkProgressAndRestartGame(counter)
                counter += 1
            }
        })
        button3.setOnClickListener(View.OnClickListener { view ->
            if(sequenceDone) {
                userListOfButtons.add(2)
                checkProgressAndRestartGame(counter)
                counter += 1
            }
        })
        button4.setOnClickListener(View.OnClickListener { view ->
            if(sequenceDone) {
                userListOfButtons.add(3)
                checkProgressAndRestartGame(counter)
                counter += 1
            }
        })
        button5.setOnClickListener(View.OnClickListener { view ->
            if(sequenceDone) {
                userListOfButtons.add(4)
                checkProgressAndRestartGame(counter)
                counter += 1
            }
        })
        button6.setOnClickListener(View.OnClickListener { view ->
            if(sequenceDone) {
                userListOfButtons.add(5)
                checkProgressAndRestartGame(counter)
                counter += 1
            }
        })

    }
    private fun highScoreLogic(scoreToCheck : Int) {
        var isNewHighScore : Boolean  = checkScore(scoreToCheck)
    }

    private fun checkScore(scoreToCheck: Int): Boolean {
        var prefs = getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE)
        var score = prefs.getInt("score5", 0) //0 is the default value
        if(scoreToCheck >= score) {
            //switch activity to fill up new data
            val prefs = getSharedPreferences("myPrefsKey", MODE_PRIVATE)
            val editor: SharedPreferences.Editor = prefs.edit()
            editor.putInt("scoreToSave", scoreToCheck)
            editor.putInt("scoreSpot", 5)
            editor.commit()
            startActivity(Intent(this, FillNewScore::class.java))
            finish()
        } else{
            score = prefs.getInt("score4", 0) //0 is the default value
            if(scoreToCheck >= score) {
                //switch activity to fill up new data
                val prefs = getSharedPreferences("myPrefsKey", MODE_PRIVATE)
                val editor: SharedPreferences.Editor = prefs.edit()
                editor.putInt("scoreToSave", scoreToCheck)
                editor.putInt("scoreSpot", 4)
                editor.commit()
                startActivity(Intent(this, FillNewScore::class.java))
                finish()
            }
            else{
                score = prefs.getInt("score3", 0) //0 is the default value
                if(scoreToCheck >= score) {
                    //switch activity to fill up new data
                    val prefs = getSharedPreferences("myPrefsKey", MODE_PRIVATE)
                    val editor: SharedPreferences.Editor = prefs.edit()
                    editor.putInt("scoreToSave", scoreToCheck)
                    editor.putInt("scoreSpot", 3)
                    editor.commit()
                    startActivity(Intent(this, FillNewScore::class.java))
                    finish()
                } else {
                    score = prefs.getInt("score2", 0) //0 is the default value
                    if(scoreToCheck >= score) {
                        //switch activity to fill up new data
                        val prefs = getSharedPreferences("myPrefsKey", MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = prefs.edit()
                        editor.putInt("scoreToSave", scoreToCheck)
                        editor.putInt("scoreSpot", 2)
                        editor.commit()
                        startActivity(Intent(this, FillNewScore::class.java))
                        finish()
                    } else {
                        score = prefs.getInt("score1", 0) //0 is the default value
                        if(scoreToCheck >= score) {
                            //switch activity to fill up new data
                            val prefs = getSharedPreferences("myPrefsKey", MODE_PRIVATE)
                            val editor: SharedPreferences.Editor = prefs.edit()
                            editor.putInt("scoreToSave", scoreToCheck)
                            editor.putInt("scoreSpot", 1)
                            editor.commit()
                            startActivity(Intent(this, FillNewScore::class.java))
                            finish()
                        }
                    }
                }
            }
        }
        return false
    }

    private fun playRandomSound(soundList: MutableList<Int>) {
        val randomInt: Int = Random.nextInt(soundList.size)
        val sound: Int = soundList[randomInt]
        val mp = MediaPlayer.create(this, sound)
        mp.start()
    }

    private fun playSound(soundNumber : Int, soundList: MutableList<Int>) {
        val sound: Int = soundList[soundNumber]
        val mp = MediaPlayer.create(this, sound)
        mp.start()
    }

    private fun displayNumber(number: Int, button: Button, id: Int, iterCounter: Int, soundList: MutableList<Int>) {
        val delay : Int = 1500*iterCounter
        var delay2 = delay.toLong()
        if(iterCounter == 1) {
            playSound(number, soundList)
            button.setBackgroundColor(Color.GRAY)
            button.setTextColor(Color.WHITE)
            button.text = iterCounter.toString()
        } else {
            Timer().schedule(delay2) {
                playSound(number, soundList)
                button.setBackgroundColor(Color.GRAY)
                button.setTextColor(Color.WHITE)
                button.text = iterCounter.toString()
            }
        }

        if(iterCounter != 1) {
            delay2 += 1500
        }
        Timer().schedule(delay2) {
            button.text = ""
            if(id == 0) {
                button.setBackgroundColor(Color.BLACK)
            }
            if(id == 1) {
                button.setBackgroundColor(Color.parseColor("#B00020"))
            }
            if(id == 2) {
                button.setBackgroundColor(Color.parseColor("#FFBB33"))
            }
            if(id == 3) {
                button.setBackgroundColor(Color.parseColor("#3700B3"))
            }
            if(id == 4) {
                button.setBackgroundColor(Color.parseColor("#AA66CC"))
            }
            if(id == 5) {
                button.setBackgroundColor(Color.parseColor("#018786"))
            }
        }
    }
}


