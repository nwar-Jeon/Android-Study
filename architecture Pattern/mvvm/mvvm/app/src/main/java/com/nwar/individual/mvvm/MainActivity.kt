package com.nwar.individual.mvvm

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.nwar.individual.mvvm.databinding.ActivityMainBinding
import com.nwar.individual.mvvm.viewModel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    val viewModel by lazy { MainActivityViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.setVariable(BR.viewmodel, viewModel)
    }
}