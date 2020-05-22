package com.nwar.individual.aac_viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CustomViewModel(val str : String) : ViewModel() {
    val string = MutableLiveData<String>().apply { value = str }
}