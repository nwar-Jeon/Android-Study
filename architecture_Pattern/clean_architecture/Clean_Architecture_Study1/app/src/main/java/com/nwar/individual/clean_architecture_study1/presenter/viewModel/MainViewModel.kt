package com.nwar.individual.clean_architecture_study1.presenter.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nwar.individual.clean_architecture_study1.domain.entity.User
import com.nwar.individual.clean_architecture_study1.domain.useCase.MainUseCase

class MainViewModel() : ViewModel(){
    val user : MutableLiveData<User> = MutableLiveData()
    val mainUseCase = MainUseCase()
    fun getUserData(){
        user.value = mainUseCase.getUserData()
    }
}