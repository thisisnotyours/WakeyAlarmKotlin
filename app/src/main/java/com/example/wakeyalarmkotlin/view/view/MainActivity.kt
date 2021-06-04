package com.example.wakeyalarmkotlin.view.view

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wakeyalarmkotlin.R
import com.example.wakeyalarmkotlin.view.adapter.DailyAlarmItemAdapter
import com.example.wakeyalarmkotlin.view.value.DailyAlarmItem
import com.google.android.material.navigation.NavigationView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateText = findViewById<TextView>(R.id.date_text)
        val dayType = findViewById<TextView>(R.id.day_type)
        val timeText = findViewById<TextView>(R.id.time_text)
        val timeTypeText = findViewById<TextView>(R.id.time_type)


        // current date & time
        val now = System.currentTimeMillis()
        val date = Date(now)
        val sdf = SimpleDateFormat("yyyy.MM.dd")
        val sdfTime = SimpleDateFormat("HH : mm")
        val sdfTimeType = SimpleDateFormat("a");  // pm / am
        val sdfDayType = SimpleDateFormat("E");  // 요일

        val strDate = sdf.format(date)
        val strTime = sdfTime.format(date)
        val strTimeType = sdfTimeType.format(date)
        val strDayType = sdfDayType.format(date)

        //setText in date & time
        dateText.text = strDate
        timeText.text = strTime
        timeTypeText.text = strTimeType
        dayType.text = "(" + strDayType + ")"


        val nav = findViewById<NavigationView>(R.id.nav)
        val drawer = findViewById<DrawerLayout>(R.id.drawer)

        val menuBtn = findViewById<ImageView>(R.id.menu_btn)
        menuBtn.setOnClickListener{
            if (!drawer.isDrawerOpen(Gravity.LEFT)){
                drawer.openDrawer(Gravity.LEFT);
            }
        }


        //onClick on NavigationView
        //nav.setItemIconTintList(null);      //to color the icon
        nav.setNavigationItemSelectedListener { menuItem ->
            val id = menuItem.itemId
            when (id) {
                R.id.info -> Toast.makeText(this, "menu1", Toast.LENGTH_SHORT).show()
                R.id.trasaction_info -> Toast.makeText(this, "menu2", Toast.LENGTH_SHORT).show()
                R.id.setting -> Toast.makeText(this, "menu3", Toast.LENGTH_SHORT).show()
                R.id.note_payment -> Toast.makeText(this, "menu4", Toast.LENGTH_SHORT).show()
                R.id.print_receipt -> Toast.makeText(this, "menu5", Toast.LENGTH_SHORT).show()
                R.id.cancel_payment -> Toast.makeText(this, "menu6", Toast.LENGTH_SHORT).show()
            }
            //switch 문 끝나고 drawer 닫기?
            // drawerLayout.closeDrawer(nav);
            false
        } //nav





        // create recyclerView
        val items = arrayListOf<DailyAlarmItem>()
        for (i in 0..8){
            items.add(DailyAlarmItem("School", "07:30","AM" ))
        }

        val dailyAlarmRecycler = findViewById<RecyclerView>(R.id.recycler)
        dailyAlarmRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = DailyAlarmItemAdapter(context, items)
        }


    }//onCreate





    fun addAlarm(view: View) {
        //transfer to addingPage
        //TODO: can apply animation when clicking?
        val intent = Intent(this, AddAlarmActivity::class.java)
        startActivity(intent)
    }


}//MainActivity...


