package com.example.abpweddingsouptik.data.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Visit(
    @SerializedName("actionType")
    val actionType: String,
    @SerializedName("createdOn")
    val createdOn: Long,
    @SerializedName("description")
    val description: String,

    @Expose(serialize = false, deserialize = false)
    var dateString: String,

    @Expose(serialize = false, deserialize = false)
    var timeString: String
)