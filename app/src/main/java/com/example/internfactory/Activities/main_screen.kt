package com.example.internfactory.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.internfactory.R
import kotlin.math.abs

class main_screen : Activity() {
    lateinit var viewpager2 : ViewPager2
    lateinit var handler: Handler
    lateinit var imageList: ArrayList<Int>


    lateinit var adapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        init()
        setUptransformer()

        viewpager2.registerOnPageChangeCallback(object : OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable,2000)

            }
        })
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable,2000)

    }

    private val runnable = Runnable{
        viewpager2.currentItem = viewpager2.currentItem + 1
    }

    private fun setUptransformer() {
        val transformer =  CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r + 0.14f
        }
        viewpager2.setPageTransformer(transformer)
    }

    private fun init(){
        viewpager2 = findViewById(R.id.viewpager2)
        handler = Handler(Looper.myLooper()!!)
        imageList = ArrayList()

        imageList.add(R.drawable.img10)
        imageList.add(R.drawable.img11)
        imageList.add(R.drawable.img12)
        imageList.add(R.drawable.img6)
        imageList.add(R.drawable.img8)

        adapter = ImageAdapter(imageList,viewpager2)
        viewpager2.adapter = adapter
        viewpager2.offscreenPageLimit = 3
        viewpager2.clipToPadding = false
        viewpager2.clipChildren = false
        viewpager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER


    }




//        var list = mutableListOf<Int>()
//
//        list.add(R.drawable.img7)
//        list.add(R.drawable.img10)
//        list.add(R.drawable.img11)
//        list.add(R.drawable.img12)
//
//
//
//        adapters = main_page_Carousel_adapter(this)
//        adapters.setContentList(list)
//
//        abc = findViewById(R.id.carousel_pageview)
//        abc.adapter = adapters

}
