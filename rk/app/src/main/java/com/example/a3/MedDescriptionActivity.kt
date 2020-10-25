package com.example.a3

import android.R.attr.data
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_med_description.*


class MedDescriptionActivity : AppCompatActivity() {
    var name: TextView? = null
    var pic: ImageView? = null
    var descr: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_med_description)

        val nameInt = intent.getStringExtra("name")
        println(nameInt)
        name = this.findViewById(R.id.textView)
        name?.setText(nameInt)

        val id: Int = intent.getIntExtra("id", 0)


        val pics = intent.getIntExtra("pic", 0)
        pic = this.findViewById(R.id.imageViewBigPic)
        pic?.setImageResource(pics)


        val description = intent.getStringArrayExtra("descr")
        descr = this.findViewById(R.id.textVieDescription)
        descr?.setText(description?.get(id))

        textVieDescription.movementMethod = ScrollingMovementMethod()
    }
}