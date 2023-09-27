package com.plcoding.daggerhiltcourse.data.datamodel

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data (
    @SerializedName("memes")
    @Expose
    var memes: List<Meme>? = null


): Parcelable