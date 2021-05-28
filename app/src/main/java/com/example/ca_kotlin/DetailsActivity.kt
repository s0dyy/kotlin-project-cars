package com.example.ca_kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.details)

        val name = intent.getStringExtra("name")
        val image = intent.getStringExtra("image")
        val id = intent.getStringExtra("id")

        val textView = findViewById<TextView>(R.id.text)
        textView.text = "nom: " + name

        val button = findViewById<Button>(R.id.return_main)

        button.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }

}