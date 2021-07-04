package com.dir.irfan_tentwenty_assignment.model.images

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ImagesData {
    @SerializedName("backdrops")
    @Expose
    var backdrops: List<Backdrop>? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("logos")
    @Expose
    var logos: List<Logo>? = null

    @SerializedName("posters")
    @Expose
    var posters: List<Poster>? = null
}