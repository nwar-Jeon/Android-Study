package com.nwar.individual.multiplicationtable

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import io.reactivex.Observable

class MainActivity : AppCompatActivity() {

    val textView by lazy { findViewById<TextView>(R.id.textview) }
    val editText by lazy { findViewById<EditText>(R.id.edittext) }
    val button by lazy { findViewById<Button>(R.id.button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            textView.text = ""
            Observable.just(editText.text.toString())
                .map { textStr -> textStr.toInt() }
                .filter { textInt -> textInt>=1 }
                .flatMap { textInt -> Observable.range(1,9)
                    .map { item -> "$textInt * $item = ${textInt*item} \n" }}
                .subscribe(textView::append) { e -> Toast.makeText(this,"onError!",Toast.LENGTH_SHORT).show()}
        }
    }
}