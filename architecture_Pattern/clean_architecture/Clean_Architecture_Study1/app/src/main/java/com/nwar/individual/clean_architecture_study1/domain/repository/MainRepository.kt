package com.nwar.individual.clean_architecture_study1.domain.repository

import com.nwar.individual.clean_architecture_study1.domain.entity.User

interface MainRepository {
    fun getDataFromDataSource() : User
}