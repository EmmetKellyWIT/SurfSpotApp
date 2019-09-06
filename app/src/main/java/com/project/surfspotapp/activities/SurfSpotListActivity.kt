package com.project.surfspotapp.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.activity_surfspot_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import com.project.surfspotapp.activities.SurfSpotAdapter
import com.project.surfspotapp.activities.SurfSpotListener
import com.project.surfspotapp.R
import com.project.surfspotapp.main.MainApp
import com.project.surfspotapp.models.SurfSpotModel




class SurfSpotListActivity : AppCompatActivity(), SurfSpotListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surfspot_list)
        app = application as MainApp

        //layout and populate for display
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager   //recyclerView is a widget in activity_surfspot_list.xml
        loadSurfSpots()


        //enable action bar and set title
        toolbarMain.title = title
        setSupportActionBar(toolbarMain)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<SurfSpotActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSurfSpotClick(surfspot: SurfSpotModel) {
        startActivityForResult(intentFor<SurfSpotActivity>().putExtra("surfspot_edit", surfspot), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //recyclerView is a widget in activity_surfspot_list.xml
        loadSurfSpots()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadSurfSpots() {
        showSurfSpots(app.surfspots.findAll())
    }

    fun showSurfSpots (surfspots: List<SurfSpotModel>) {
        recyclerView.adapter = SurfSpotAdapter(surfspots, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }




}