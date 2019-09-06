package com.project.surfspotapp.models


import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import com.project.surfspotapp.helpers.*
import java.util.*

val JSON_FILE = "surfspots.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<ArrayList<SurfSpotModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class SurfSpotJSONStore : SurfSpotStore, AnkoLogger {

    val context: Context
    var surfspots = mutableListOf<SurfSpotModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<SurfSpotModel> {
        return surfspots
    }

    override fun create(surfspot: SurfSpotModel) {
        surfspot.id = generateRandomId()
        surfspots.add(surfspot)
        serialize()
    }


    override fun update(surfspot: SurfSpotModel) {
        val surfspotsList = findAll() as ArrayList<SurfSpotModel>
        var foundSurfSpot: SurfSpotModel? = surfspotsList.find { p -> p.id == surfspot.id }
        if (foundSurfSpot != null) {
            foundSurfSpot.title = surfspot.title
            foundSurfSpot.description = surfspot.description
            foundSurfSpot.warnings = surfspot.warnings
            foundSurfSpot.image = surfspot.image
            //foundSurfSpot.lat = surfspot.lat
            // foundSurfSpot.lng = surfspot.lng
            // foundSurfSpot.zoom = surfspot.zoom
        }
        serialize()
    }


    override fun delete(surfspot: SurfSpotModel) {
        surfspots.remove(surfspot)
        serialize()
    }


    private fun serialize() {
        val jsonString = gsonBuilder.toJson(surfspots, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        surfspots = Gson().fromJson(jsonString, listType)
    }
}