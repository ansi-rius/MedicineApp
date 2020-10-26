package com.example.a3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class MainFragment : Fragment() {

    var navController: NavController? = null
    private var mButtonOpenMap: Button? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.buttonMedList).setOnClickListener{
            navController!!.navigate(R.id.action_mainFragment_to_medListFragment)
        }
        view.findViewById<Button>(R.id.buttonMap).setOnClickListener {
            val location = Uri.parse("geo:0,0?q=Moscow")
            val mapIntent = Intent(Intent.ACTION_VIEW, location)
            startActivity(mapIntent)
        }

        fetchJson()

    }

    fun fetchJson() {
        val url = "https://jsonplaceholder.typicode.com/posts"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override  fun onResponse(call: Call?, response: Response?){
                val body = response?.body()?.string()
                println(body)

                val gson = GsonBuilder().create()

                val news = gson.fromJson(body, NewsList::class.java)



            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to exec req")
            }
        })

    }
}

class News(val id: Int, val userId: Int, val title: String, val body: String)
class NewsList(val news: List<News>)