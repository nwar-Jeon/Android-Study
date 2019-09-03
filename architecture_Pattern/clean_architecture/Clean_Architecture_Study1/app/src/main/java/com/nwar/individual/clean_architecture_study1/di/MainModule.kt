package com.nwar.individual.clean_architecture_study1.di

import com.nwar.individual.clean_architecture_study1.data.dataSource.MainDataSource
import com.nwar.individual.clean_architecture_study1.data.dataSource.MainDataSourceImpl
import com.nwar.individual.clean_architecture_study1.data.repository.MainRepositoryImpl
import com.nwar.individual.clean_architecture_study1.domain.repository.MainRepository
import dagger.Module
import dagger.Provides

@Module
class MainModule() {
    @Provides
    fun provideRepository(dataSource : MainDataSource) : MainRepository = MainRepositoryImpl(dataSource)

    @Provides
    fun provideDataSource() : MainDataSource = MainDataSourceImpl()
}