package com.example.abpweddingsouptik.data.model


import com.google.gson.annotations.SerializedName

data class ResponseHeader(
    @SerializedName("natbs")
    val natbs: Int,
    @SerializedName("numFound")
    val numFound: Int,
    @SerializedName("rows")
    val rows: Int,
    @SerializedName("start")
    val start: Int,
    @SerializedName("status")
    val status: Int,
    @SerializedName("statusMessage")
    val statusMessage: Any
)