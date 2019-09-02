package com.nwar.individual.clean_architecture_study1.presenter.viewModel

import androidx.lifecycle.ViewModel
import com.nwar.individual.clean_architecture_study1.domain.useCase.MainUseCase

class MainViewModel() : ViewModel(){
    val mainUseCase = MainUseCase()
}