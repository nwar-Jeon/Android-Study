package com.nwar.individual.clean_architecture_study1.presenter.userInterface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.nwar.individual.clean_architecture_study1.R
import com.nwar.individual.clean_architecture_study1.presenter.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this)[MainViewModel::class.java]
    }
}
