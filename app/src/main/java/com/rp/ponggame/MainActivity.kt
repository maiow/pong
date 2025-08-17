package com.rp.ponggame

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var pongView: PongView
    private lateinit var scoreText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pongView = findViewById(R.id.pongView)
        scoreText = findViewById(R.id.scoreText)

        pongView.setScoreListener { score ->
            scoreText.text = getString(R.string.score_text, score)
        }
    }
}
