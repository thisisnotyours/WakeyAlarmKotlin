package com.example.wakeyalarmkotlin.view.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.wakeyalarmkotlin.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import java.util.*


class MainAlarmActivity : AppCompatActivity() {
    var allTabs: TabLayout? = null
    private var mContext: Context? = null
    var bottomNav: BottomNavigationView? = null
    var frameLayout: FrameLayout? = null
    var fragmentStack: Stack<Fragment>? = null
    var fragmentManager: FragmentManager? = null
    var alarmFragment: AlarmFragment = AlarmFragment(R.layout.fragment_alarm)
    var clockFragment: ClockFragment = ClockFragment(R.layout.fragment_clock)
    var timerFragment: TimerFragment = TimerFragment(R.layout.fragment_timer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_alarm)

        mContext = this

        allTabs = findViewById(R.id.tabs)
        bindWidgetWithEvent()
        setUpTabLayout()



        /* first fragment that user see */
        frameLayout = findViewById(R.id.frame_layout)
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, alarmFragment).commit()



/*
        *//* BottomNavigation *//*
        var bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigationView.setOnNavigationItemReselectedListener { item ->
            when(item.itemId){
                R.id.alarm -> {
                    Toast.makeText(this, "Alarm", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout, alarmFragment).commit()
                    true
                }
                R.id.clock -> {
                    Toast.makeText(this, "Clock", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout, clockFragment).commit()
                    true
                }
                R.id.timer -> {
                    Toast.makeText(this, "Timer", Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout, timerFragment).commit()
                    true
                }
            }
        }*/

    }//onCreate



    private fun setUpTabLayout(){
        alarmFragment = AlarmFragment(R.layout.fragment_alarm)
        clockFragment = ClockFragment(R.layout.fragment_clock)
        timerFragment = TimerFragment(R.layout.fragment_timer)
        allTabs?.addTab(allTabs!!.newTab().setText(mContext?.getString(R.string.alarm)), true)
        allTabs?.addTab(allTabs!!.newTab().setText(mContext?.getString(R.string.clock)), true)
        allTabs?.addTab(allTabs!!.newTab().setText(mContext?.getString(R.string.timer)), true)
    }


    private fun bindWidgetWithEvent(){
        allTabs!!.setOnClickListener(object : OnTabSelectedListener, View.OnClickListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    setCurrentTabFragment(tab.position)
                }
            }

            override fun onClick(v: View?) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun setCurrentTabFragment(tabPosition: Int){
        when (tabPosition){
            0 -> replaceFragment(alarmFragment)
            1 -> replaceFragment(clockFragment)
            2 -> replaceFragment(timerFragment)
        }
    }

    /*private fun setCurrentTabFragment(tabPosition: Int) {
        when (tabPosition) {
            0 -> replaceFragment(fragmentOne)
            1 -> replaceFragment(fragmentTwo)
        }
    }*/


    private fun replaceFragment(fragment: Fragment){
        val fm = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        ft.replace(R.id.frame_layout, fragment)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ft.commit()

    }


}//AddAlarmActivity

