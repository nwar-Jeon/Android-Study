package com.nwar.individual.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class Model() {
    val name : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun setObserve(owner : LifecycleOwner, observer : Observer<String>){
        name.observe(owner, observer)
    }
}