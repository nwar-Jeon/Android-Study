package com.nwar.individual.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nwar.individual.livedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val model = Model()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activity = this
        binding. = model

        val textView = findViewById<TextView>(R.id.textview)
        val observer = Observer<String>({it -> textView.text = it})
        val button = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.editText)

        button.setOnClickListener {
            model.name.value = editText.text.toString()
        }
        model.name.observe(this, observer)
    }
}
