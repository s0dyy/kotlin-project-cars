package com.example.ca_kotlin.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Vehicles(
    @Expose
    @SerializedName("id")
    val vehicleId: Long,

    @Expose
    @SerializedName("nom")
    val name: String,

    @Expose
    @SerializedName("prixjournalierbase")
    val price: String,

    @Expose
    @SerializedName("categorieco2")
    val category: String,

    @Expose
    @SerializedName("image")
    val image: String
) {}