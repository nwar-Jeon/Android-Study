package com.nwar.individual.clean_architecture_study1.localData

import com.google.gson.Gson
import com.nwar.individual.clean_architecture_study1.domain.entity.User
import org.intellij.lang.annotations.Language

fun getDummyData() : User = Gson().fromJson(data, User::class.java)

@Language("JSON")
val data : String = "{\n  \"id\" : 0,\n  \"name\" : \"user1\",\n  \"age\" : 17\n}"