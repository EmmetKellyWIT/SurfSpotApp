package com.project.surfspotapp.activities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.surfspotapp.R
import kotlinx.android.synthetic.main.activity_surfspot.view.*
import kotlinx.android.synthetic.main.card_surfspot.view.*
import com.project.surfspotapp.helpers.readImageFromPath
import com.project.surfspotapp.models.SurfSpotModel



interface SurfSpotListener {
    fun onSurfSpotClick(surfspot: SurfSpotModel)
}

class SurfSpotAdapter constructor(private var surfspots: List<SurfSpotModel>,
                                   private val listener: SurfSpotListener) : RecyclerView.Adapter<SurfSpotAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_surfspot, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val surfspot = surfspots[holder.adapterPosition]
        holder.bind(surfspot, listener)
    }

    override fun getItemCount(): Int = surfspots.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(surfspot: SurfSpotModel,  listener : SurfSpotListener) {
            itemView.surfspotTitleList.text= surfspot.title
            itemView.surfspotDescriptionList.text = surfspot.description
            itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, surfspot.image))
            itemView.setOnClickListener { listener.onSurfSpotClick(surfspot) }
        }
    }
}
