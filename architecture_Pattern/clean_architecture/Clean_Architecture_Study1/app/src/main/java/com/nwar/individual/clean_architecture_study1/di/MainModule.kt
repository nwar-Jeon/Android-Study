package com.nwar.individual.clean_architecture_study1.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.nwar.individual.clean_architecture_study1.data.dataSource.MainDataSource
import com.nwar.individual.clean_architecture_study1.data.dataSource.MainDataSourceImpl
import com.nwar.individual.clean_architecture_study1.data.repository.MainRepositoryImpl
import com.nwar.individual.clean_architecture_study1.domain.repository.MainRepository
import com.nwar.individual.clean_architecture_study1.domain.useCase.MainUseCase
import com.nwar.individual.clean_architecture_study1.presenter.viewModel.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class MainModule() {

    @Provides
    fun provideFactory(useCase : MainUseCase) = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(useCase) as T
        }
    }

    @Provides
    fun provideUseCase(repository : MainRepository) : MainUseCase = MainUseCase(repository)

    @Provides
    fun provideRepository(dataSource : MainDataSource) : MainRepository = MainRepositoryImpl(dataSource)

    @Provides
    fun provideDataSource() : MainDataSource = MainDataSourceImpl()
}