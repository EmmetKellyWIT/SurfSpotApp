package com.project.surfspotapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize




@Parcelize
data class SurfSpotModel (var id: Long =0,
                           var title: String = "",
                           var description: String = "",
                           var warnings: String = "",
                           var image: String = "") : Parcelable {
}