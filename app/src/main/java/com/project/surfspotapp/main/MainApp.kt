package com.project.surfspotapp.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import com.project.surfspotapp.models.SurfSpotJSONStore
import com.project.surfspotapp.models.SurfSpotMemStore
import com.project.surfspotapp.models.SurfSpotModel
import com.project.surfspotapp.models.SurfSpotStore


class MainApp: Application(), AnkoLogger {

    lateinit var surfspots: SurfSpotStore

    override fun onCreate() {
        super.onCreate()
        surfspots = SurfSpotJSONStore(applicationContext)
        //surfspots = SurfSpotMemStore()
        info("SurfSpot Started")
    }
}