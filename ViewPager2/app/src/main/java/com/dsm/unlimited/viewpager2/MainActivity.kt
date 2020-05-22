package com.dsm.unlimited.viewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    val viewpager by lazy { findViewById<ViewPager2>(R.id.viewpager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewpager.adapter = Adapter()
        viewpager.apply {
            offscreenPageLimit = 2
            setPadding(200,0,200,0)
            setPageTransformer { page, position ->
                page.scaleX = 1 - Math.abs(position) * 0.3f
                page.scaleY = 1 - Math.abs(position) * 0.3f
            }
        }
    }

    inner class Adapter() : RecyclerView.Adapter<Adapter.ViewHolder>() {
        override fun getItemCount(): Int = 5

        override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {}

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder =
            ViewHolder(LayoutInflater.from(this@MainActivity).inflate(R.layout.item, parent, false))

        inner class ViewHolder(val v: View) : RecyclerView.ViewHolder(v) {

        }
    }
}
