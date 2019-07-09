package com.nwar.individual.mvvm.viewModel

import android.databinding.ObservableField

class MainActivityViewModel(){
    val name = ObservableField("")
    init {
        name.set("이름")
    }
    fun clickName() {
        name.set("클릭!")
    }
}