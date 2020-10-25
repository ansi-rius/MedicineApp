package com.example.a3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Adapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class MainActivity : AppCompatActivity() {
    lateinit var NavControler: NavController

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

        NavControler = findNavController(R.id.nav_fragment)
    }
    private fun medItemClicked(med: Medecine) {
        Toast.makeText(this, "Clicked: ${med.medecineName[med.id]}", Toast.LENGTH_SHORT).show()
    }


}