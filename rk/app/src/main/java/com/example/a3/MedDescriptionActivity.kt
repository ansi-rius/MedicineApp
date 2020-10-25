package com.example.a3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MedDescriptionActivity : AppCompatActivity() {
    var name: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_med_description)
        val nameInt = intent.getStringExtra("name")
        println(nameInt)
        name = this.findViewById(R.id.textView)
        name?.setText(nameInt)

    }
}