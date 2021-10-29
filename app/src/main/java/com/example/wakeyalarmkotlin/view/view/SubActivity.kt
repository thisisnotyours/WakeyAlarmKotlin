package com.example.wakeyalarmkotlin.view.view

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.wakeyalarmkotlin.R
import com.example.wakeyalarmkotlin.view.adapter.MPagerAdapter
import com.google.android.material.tabs.TabLayout

class SubActivity : AppCompatActivity() {

    lateinit var mfragment: View
    lateinit var pager: ViewPager
    lateinit var tablayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_sub)

        //setup view pager
//        var adapter = MPagerAdapter(fragmentManager!!)
//        adapter.addFragment()

    }

}//Activity