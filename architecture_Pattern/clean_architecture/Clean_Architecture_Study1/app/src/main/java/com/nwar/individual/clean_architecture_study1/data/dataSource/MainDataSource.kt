package com.nwar.individual.clean_architecture_study1.data.dataSource

import com.nwar.individual.clean_architecture_study1.domain.entity.User

interface MainDataSource {
    fun getDataFromDummy() : User
}