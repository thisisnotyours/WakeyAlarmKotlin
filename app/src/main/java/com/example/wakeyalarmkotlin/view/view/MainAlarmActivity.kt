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
import com.example.wakeyalarmkotlin.view.fragment.AlarmFragment
import com.example.wakeyalarmkotlin.view.fragment.ClockFragment
import com.example.wakeyalarmkotlin.view.fragment.TimerFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import java.util.*


class MainAlarmActivity : AppCompatActivity() {
    var tabLayout: TabLayout? = null
    private var mContext: Context? = null
    var bottomNav: BottomNavigationView? = null
    var frameLayout: FrameLayout? = null
    var fragmentStack: Stack<Fragment>? = null
    var fragmentManager: FragmentManager? = null
    var alarmFragment: AlarmFragment? = null
    var clockFragment: ClockFragment? = null
    var timerFragment: TimerFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_alarm)

        mContext = this


        //create fragments objects  //각 프래그먼트들 객체화
        alarmFragment = AlarmFragment(R.layout.fragment_alarm)
        clockFragment = ClockFragment(R.layout.fragment_clock)
        timerFragment = TimerFragment(R.layout.fragment_timer)


        /* first fragment that user see */
        frameLayout = findViewById(R.id.frame_layout)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, alarmFragment!!)
            .commit()


        tabLayout = findViewById(R.id.tablayout)
        tabLayout?.addTab(tabLayout!!.newTab().setText(mContext?.getString(R.string.alarm)))
        tabLayout?.addTab(tabLayout!!.newTab().setText(mContext?.getString(R.string.clock)))
        tabLayout?.addTab(tabLayout!!.newTab().setText(mContext?.getString(R.string.timer)))


        bindWidgetWithEvent()

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





    private fun bindWidgetWithEvent(){
        tabLayout!!.setOnClickListener(object : OnTabSelectedListener, View.OnClickListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    Log.d("tabs>","tab is not null, "+tab.position)
                    setCurrentTabFragment(tab.position)
                    Toast.makeText(this@MainAlarmActivity, "click tab-> "+tab, Toast.LENGTH_SHORT).show()
                    Log.d("tab-> ", tab.toString())
                }

            }

            override fun onClick(v: View?) {

            }
        })
    }

    private fun setCurrentTabFragment(tabPosition: Int){
        when (tabPosition){
            0 -> alarmFragment?.let { replaceFragment(it) }
            1 -> clockFragment?.let { replaceFragment(it) }
            2 -> timerFragment?.let { replaceFragment(it) }
        }
    }



    private fun replaceFragment(fragment: Fragment){
        val fm = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        ft.replace(R.id.frame_layout, fragment)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ft.commit()

    }


}//AddAlarmActivity

