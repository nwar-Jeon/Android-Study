package com.nwar.individual.clean_architecture_study1.domain.useCase

import com.nwar.individual.clean_architecture_study1.domain.entity.User
import com.nwar.individual.clean_architecture_study1.domain.repository.MainRepository
import javax.inject.Inject

class MainUseCase()
{
    @Inject
    lateinit var mainRepository : MainRepository
    init {

    }
    fun getUserData() : User {
        return mainRepository.getDataFromDataSource()
    }
}