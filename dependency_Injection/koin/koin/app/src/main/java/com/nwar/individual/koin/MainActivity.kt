package com.nwar.individual.koin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.core.inject

class MainActivity : AppCompatActivity() {
    val a : A by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startKoin {
            koiin
        }

        val a = get()
    }
}

val koiin = module {
    factory { A() }
}

class A(){

}