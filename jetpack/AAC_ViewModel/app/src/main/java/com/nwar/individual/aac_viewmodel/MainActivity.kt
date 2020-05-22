package com.nwar.individual.aac_viewmodel


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.fragment.app.FragmentManager
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()
        val manager = supportFragmentManager

        findViewById<Button>(R.id.button).setOnClickListener {
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.fragment,secondFragment)
            transaction.commit()
        }
    }
}
