package com.nwar.individual.clean_architecture_study1.presenter.userInterface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.nwar.individual.clean_architecture_study1.R
import com.nwar.individual.clean_architecture_study1.di.DaggerMainComponent
import com.nwar.individual.clean_architecture_study1.di.MainModule
import com.nwar.individual.clean_architecture_study1.domain.useCase.MainUseCase
import com.nwar.individual.clean_architecture_study1.presenter.viewModel.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory : ViewModelProvider.Factory
    val viewModel : MainViewModel by lazy {
        ViewModelProviders.of(this,factory)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMainComponent.builder().mainModule(MainModule()).build().inject(this)

        viewModel.getUserData()
        Log.i("Dagger 2", viewModel.user.value?.name)
    }
}
