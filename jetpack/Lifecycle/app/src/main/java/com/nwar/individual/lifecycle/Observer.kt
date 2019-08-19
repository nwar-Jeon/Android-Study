package com.nwar.individual.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

private const val TAG = "LifeCycle"
class Observer(private val lifecycle : Lifecycle) : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun create(){
        Log.i(TAG, "onCreated")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start(){
        Log.i(TAG, "onStarted")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy(){
        Log.i(TAG, "onDestroyed")
    }
}