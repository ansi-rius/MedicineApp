package com.example

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a3.MedDescriptionActivity
import kotlinx.android.synthetic.main.list_item_view.view.*
import com.example.a3.Medecine
import com.example.a3.R

class Adapter(val items: List<Medecine>, private val clickListener: (Medecine) -> Unit) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {



    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(items[position], clickListener)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var largeTextView: TextView? = null
        var smallTextView: TextView? = null
        var picImageView: ImageView? = null
        //lateinit var NavControler: NavController



        init {
            largeTextView = itemView?.findViewById(R.id.textViewLarge)
            smallTextView = itemView?.findViewById(R.id.textViewSmall)
            picImageView = itemView?.findViewById(R.id.imageView)
            //NavControler = Navigation.findNavController(itemView)
        }

        fun bind(med: Medecine, clickListener: (Medecine) -> Unit) {

            itemView.textViewLarge.text = med.medecineName[med.id]
            itemView.textViewSmall.text = "more info"

            itemView.imageView.setImageResource(med.pics[med.id])

            itemView.setOnClickListener { clickListener(med) }
            itemView.setOnClickListener{
                //itemView.setBackgroundColor(Color.parseColor("#C8C8C8"))
                //Navigation
                val intent = Intent(itemView.context, MedDescriptionActivity::class.java)
                intent.putExtra("name", itemView.textViewLarge.text)
                intent.putExtra("id", med.id)
                intent.putExtra("pic", med.pics[med.id])
                intent.putExtra("descr", med.description)
                itemView.context.startActivity(intent)

                //NavControler.navigate(R.id.action_homeFragment_to_description) //action_homeFragment_to_description




            }
        }
    }


}