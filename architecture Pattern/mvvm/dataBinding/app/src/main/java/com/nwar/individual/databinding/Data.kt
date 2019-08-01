package com.nwar.individual.databinding

import android.databinding.ObservableField
import android.text.Editable
import android.text.TextWatcher
import android.util.Log

data class Data(
    val textStr : ObservableField<String> = ObservableField()
) {
    fun onTextChange(str : CharSequence?, start : Int, bef : Int, cnt : Int){
        Log.e("DATA", str?.toString() ?: "nothing")
        textStr.set(str?.toString() ?: "nothing")
    }
}