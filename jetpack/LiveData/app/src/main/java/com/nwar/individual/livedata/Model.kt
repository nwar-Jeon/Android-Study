package com.nwar.individual.livedata

import androidx.lifecycle.MutableLiveData

class Model() {
    val name : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}