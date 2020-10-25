package com.example.a3

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_view.view.*
import android.content.res.Resources
import androidx.appcompat.content.res.AppCompatResources.getDrawable

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
        init {
            largeTextView = itemView?.findViewById(R.id.textViewLarge)
            smallTextView = itemView?.findViewById(R.id.textViewSmall)
            picImageView = itemView?.findViewById(R.id.imageView)
        }

        fun bind(med: Medecine, clickListener: (Medecine) -> Unit) {
            itemView.textViewLarge.text = med.medecineName[med.id]
            itemView.textViewSmall.text = "more info"

            itemView.imageView.setImageResource(med.pics[med.id])

            itemView.setOnClickListener { clickListener(med) }
            itemView.setOnClickListener{ itemView.setBackgroundColor(Color.parseColor("#C8C8C8")) }
        }
    }
}