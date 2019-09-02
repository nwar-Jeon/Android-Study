package com.nwar.individual.clean_architecture_study1.data.repository

import com.nwar.individual.clean_architecture_study1.data.dataSource.MainDataSource
import com.nwar.individual.clean_architecture_study1.domain.entity.User
import com.nwar.individual.clean_architecture_study1.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl() : MainRepository {
    @Inject
    lateinit var mainDataSource : MainDataSource

    override fun getDataFromDataSource(): User = mainDataSource.getDataFromDummy()
}