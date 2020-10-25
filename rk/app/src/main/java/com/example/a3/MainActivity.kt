package com.example.a3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var listOfMedecines: ArrayList<Medecine> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView (R.layout.activity_main)

        for (i in 0..15) {
            val med = Medecine()
            med.id = i
            listOfMedecines.add(med)
        }
        my_recycler_view.layoutManager = LinearLayoutManager(this)
        my_recycler_view.setHasFixedSize(true)
        my_recycler_view.adapter = Adapter(listOfMedecines, { med -> medItemClicked(med) })
    }
    private fun medItemClicked(med: Medecine) {
        Toast.makeText(this, "Clicked: ${med.medecineName[med.id]}", Toast.LENGTH_SHORT).show()
    }


}