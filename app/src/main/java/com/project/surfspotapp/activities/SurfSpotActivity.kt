package com.project.surfspotapp.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
//import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_surfspot.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import com.project.surfspotapp.R
import com.project.surfspotapp.helpers.readImage
import com.project.surfspotapp.helpers.readImageFromPath
import com.project.surfspotapp.helpers.showImagePicker
import com.project.surfspotapp.main.MainApp
import com.project.surfspotapp.models.SurfSpotModel

class SurfSpotActivity : AppCompatActivity(), AnkoLogger {

    var surfspot = SurfSpotModel()
    lateinit var app: MainApp
    var edit = false
    val IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surfspot)
        app = application as MainApp

        if (intent.hasExtra("surfspot_edit"))
        {
            surfspot = intent.extras.getParcelable<SurfSpotModel>("surfspot_edit")
            surfspotTitle.setText(surfspot.title)
            surfspotDescription.setText(surfspot.description)
            surfspotWarnings.setText(surfspot.warnings)
            surfspotImage.setImageBitmap(readImageFromPath(this, surfspot.image))
            if (surfspot.image != null){
                chooseImage.setText(R.string.change_surfspot_image)
            }

            btnAdd.setText(R.string.button_saveSurfSpot)
            edit = true
        }

        btnAdd.setOnClickListener() {
            surfspot.title = surfspotTitle.text.toString()
            surfspot.description = surfspotDescription.text.toString()
            surfspot.warnings = surfspotWarnings.text.toString()
            if (surfspot.title.isNotEmpty()) {
                if (edit){
                    app.surfspots.update(surfspot.copy())
                }
                else {
                    app.surfspots.create(surfspot.copy())
                }
                info("Add Button Pressed. name: ${surfspot.title}")
                setResult(RESULT_OK)
                finish()
            }
            else {
                toast (R.string.message_enter_title)
            }
        }

        chooseImage.setOnClickListener{
            showImagePicker(this, IMAGE_REQUEST)
        }

        //Add action bar and set title
        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_surfspot, menu)
        if (edit && menu != null) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_delete -> {
                app.surfspots.delete(surfspot)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode){
            IMAGE_REQUEST -> {
                if (data !=null){
                    surfspot.image = data.getData().toString()
                    surfspotImage.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage.setText(R.string.change_surfspot_image)
                }
            }
        }
    }
}