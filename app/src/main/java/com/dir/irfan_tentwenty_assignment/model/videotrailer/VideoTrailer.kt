package com.dir.irfan_tentwenty_assignment.model.videotrailer

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VideoTrailer {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("results")
    @Expose
    var results: List<Result>? = null
}