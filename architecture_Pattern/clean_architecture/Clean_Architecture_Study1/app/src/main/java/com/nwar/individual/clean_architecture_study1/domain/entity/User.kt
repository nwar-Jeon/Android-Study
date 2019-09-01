package com.nwar.individual.clean_architecture_study1.domain.entity

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("age")
    val age : Int
)