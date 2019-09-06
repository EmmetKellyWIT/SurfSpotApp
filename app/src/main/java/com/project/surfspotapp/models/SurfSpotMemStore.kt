package com.project.surfspotapp.models


import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class SurfSpotMemStore : SurfSpotStore, AnkoLogger {

    val surfspots = ArrayList<SurfSpotModel>()

    override fun findAll(): List<SurfSpotModel> {
        return surfspots
    }

    override fun create(surfspot: SurfSpotModel) {
        surfspot.id = getId()
        surfspots.add(surfspot)
        logAll()
    }

    override fun update(surfspot: SurfSpotModel) {
        var foundSurfSpot: SurfSpotModel? = surfspots.find { p -> p.id == surfspot.id }
        if (foundSurfSpot != null) {
            foundSurfSpot.title = surfspot.title
            foundSurfSpot.description = surfspot.description
            foundSurfSpot.warnings = surfspot.warnings
            foundSurfSpot.image = surfspot.image
            logAll()
        }
    }

    override fun delete(surfspot: SurfSpotModel) {
        surfspots.remove(surfspot)
    }

    fun logAll() {
        surfspots.forEach { info("${it}") }
    }
}
