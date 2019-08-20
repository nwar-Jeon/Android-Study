package com.nwar.individual.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.nwar.individual.livedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val model = Model()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activity = this
        binding.model = model

        val observer = Observer<String>{binding.textview.text = it}

        model.setObserve(this,observer)
    }
}
