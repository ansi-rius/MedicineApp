package com.example.a3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.*
import org.json.JSONObject
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
        val url = "https://reqres.in/api/users/2"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override  fun onResponse(call: Call?, response: Response?){
                val body = response?.body()?.string()
                println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
                println(body)

                val gson = GsonBuilder().create()

                //val news = gson.fromJson(body, NewsList::class.java)

                val jsonObject = JSONObject(body)
                val news = jsonObject.getJSONObject("ad").getString("url")

                //val news: NewsList = Gson().fromJson(body, NewsList::class.java)
                var textView: TextView? = null
                textView = activity?.findViewById(R.id.textViewNews)
                textView?.text = news
                println("CheckNews")
                println(news.toString())

            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to exec req")
            }
        })

    }
}

/*
*
* {
    "data": {
        "id": 2,
        "email": "janet.weaver@reqres.in",
        "first_name": "Janet",
        "last_name": "Weaver",
        "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg"
    },
    "ad": {
        "company": "StatusCode Weekly",
        "url": "http://statuscode.org/",
        "text": "A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things."
    }
}
* */

class News(val data: DataCustome, val ad: AD)
class DataCustome(val id: Int, val email: String, val first_name: String, val last_name: String, val avatar: String)
class AD(val company: String, val url: String, val text: String)
class NewsList(val news: News)