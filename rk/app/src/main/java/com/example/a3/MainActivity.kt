package com.example.a3

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Adapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_med_list.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var NavControler: NavController

    var listOfMedecines: ArrayList<Medecine> = ArrayList()
    var listOfMedecinesSearch: ArrayList<Medecine> = ArrayList()

    var themeStat = false
/*
    var appSettingPrefs: SharedPreferences = getSharedPreferences("AppSettingPrefs", 0)
    var sharedPrefsEdit: SharedPreferences.Editor = appSettingPrefs.edit()
    var isNightModeOn: Boolean = appSettingPrefs.getBoolean("NightMode", false)
*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView (R.layout.activity_main)


        for (i in 0..15) {
            val med = Medecine()
            med.id = i
            listOfMedecines.add(med)
            listOfMedecinesSearch.add(med)
        }
        /*my_recycler_view.layoutManager = LinearLayoutManager(this)
        my_recycler_view.setHasFixedSize(true)
        my_recycler_view.adapter = Adapter(listOfMedecinesSearch, { med -> medItemClicked(med) })*/

        NavControler = findNavController(R.id.nav_fragment)
    }
    private fun medItemClicked(med: Medecine) {
        Toast.makeText(this, "Clicked: ${med.medecineName[med.id]}", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val menuItem = menu!!.findItem(R.id.search)

        if (menuItem != null){
            val searchView = menuItem.actionView as SearchView

            //val editText = searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
            //editText.hint = "Search..."

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText!!.isNotEmpty()){

                        listOfMedecinesSearch.clear()
                        val search = newText.toLowerCase(Locale.getDefault())
                        listOfMedecines.forEach{
                            if (it.medecineName[it.id].toLowerCase(Locale.getDefault()).contains(search)){
                                listOfMedecinesSearch.add(it)
                            }
                        }
                        my_recycler_view.adapter!!.notifyDataSetChanged()
                    }
                    else{
                        listOfMedecinesSearch.clear()
                        listOfMedecinesSearch.addAll(listOfMedecines)
                        my_recycler_view.adapter!!.notifyDataSetChanged()
                    }

                    return true
                }
            })
        }


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId){
            R.id.darkTheme -> changeTheme()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun changeTheme(){
        if (themeStat == false){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            themeStat = true
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            themeStat = false
        }
        /*
        if (isNightModeOn){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            sharedPrefsEdit.putBoolean("NightMode", false)
            sharedPrefsEdit.apply()
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            sharedPrefsEdit.putBoolean("NightMode", true)
            sharedPrefsEdit.apply()
        }*/
    }

}