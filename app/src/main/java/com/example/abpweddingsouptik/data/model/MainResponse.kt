package com.example.abpweddingsouptik.data.model

import com.google.gson.annotations.SerializedName

data class MainResponse(
    @SerializedName("activityDocuments")
    val activityDocuments:  Map<Long, List<Visit>>,
    @SerializedName("authentication")
    val authentication: String,
    @SerializedName("msgtype")
    val msgtype: Int,
    @SerializedName("responseHeader")
    val responseHeader: ResponseHeader
)