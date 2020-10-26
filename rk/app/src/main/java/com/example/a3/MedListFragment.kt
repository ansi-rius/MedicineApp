package com.example.a3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.ListAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Adapter
import kotlinx.android.synthetic.main.fragment_med_list.*
import java.util.*
import kotlin.collections.ArrayList


class MedListFragment : Fragment() {

    var listOfMedecines: ArrayList<Medecine> = ArrayList()
    var listOfMedecinesSearch: ArrayList<Medecine> = ArrayList()
    var themeStat = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_med_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        for (i in 0..15) {
            val med = Medecine()
            med.id = i
            listOfMedecines.add(med)
            listOfMedecinesSearch.add(med)
        }
        my_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = Adapter(listOfMedecinesSearch, { med -> medItemClicked(med) })
        }
    }
    private fun medItemClicked(med: Medecine) {
        Toast.makeText(this.context, "Clicked: ${med.medecineName[med.id]}", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)

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


        return super.onCreateOptionsMenu(menu, inflater)
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