package com.example.ca_kotlin.adapters

import android.content.Intent
import com.example.ca_kotlin.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ca_kotlin.DetailsActivity
import com.example.ca_kotlin.api.Vehicles
import com.squareup.picasso.Picasso

class VehicleAdapter(list: ArrayList<Vehicles>) : RecyclerView.Adapter<VehicleAdapter.VehiculeViewHolder>() {

    private var list: ArrayList<Vehicles> = ArrayList()

    init {
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiculeViewHolder {
        val viewMemo: View =
            LayoutInflater.from(parent.context).inflate(R.layout.vehicle_selector_item, parent, false)
        return VehiculeViewHolder(viewMemo)
    }

    override fun onBindViewHolder(holder: VehiculeViewHolder, position: Int) {
        holder.name?.text = list[position].name
        Picasso.get()
            .load("http://s519716619.onlinehome.fr/exchange/madrental/images/" + list[position].image)
            .into(holder.image!!)
    }

    fun update(list: ArrayList<Vehicles>) {
        this.list = list
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class VehiculeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView? = null
        var image: ImageView? = null

        init {
            name = itemView.findViewById(R.id.name)
            image = itemView.findViewById(R.id.image)

            itemView.setOnClickListener {
                val context = itemView.context

                println(list[adapterPosition])
                context.startActivity(Intent(context, DetailsActivity::class.java)
                    .putExtra("name", list[adapterPosition].name)
                    .putExtra("image", list[adapterPosition].image)
                    .putExtra("id", list[adapterPosition].vehicleId.toString())
                )
            }
        }
    }

}