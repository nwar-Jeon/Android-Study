package com.nwar.individual.clean_architecture_study1.presenter.userInterface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.nwar.individual.clean_architecture_study1.R
import com.nwar.individual.clean_architecture_study1.di.DaggerMainComponent
import com.nwar.individual.clean_architecture_study1.presenter.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    val viewModel : MainViewModel by lazy {
        val factory = DaggerMainComponent.builder().build().testInject()
        ViewModelProviders.of(this,factory)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        viewModel.getUserData()
        Log.i("Dagger 2", viewModel.user.value?.name)
    }
}
