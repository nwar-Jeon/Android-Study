package com.nwar.individual.databinding

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nwar.individual.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.data = Data()
    }
}
