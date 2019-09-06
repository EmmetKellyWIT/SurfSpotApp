package com.project.surfspotapp.models

interface SurfSpotStore {
    fun findAll(): List<SurfSpotModel>
    fun create(surfspot: SurfSpotModel)
    fun update(surfspot: SurfSpotModel)
    fun delete(surfspot: SurfSpotModel)


}