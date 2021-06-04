package com.example.wakeyalarmkotlin.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wakeyalarmkotlin.R
import com.example.wakeyalarmkotlin.view.adapter.DailyAlarmItemAdapter
import com.example.wakeyalarmkotlin.view.value.DailyAlarmItem
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
        val sdfTime = SimpleDateFormat("HH:mm")
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





        // create recyclerView
        val items = arrayListOf<DailyAlarmItem>()
        for (i in 0..2){
            items.add(DailyAlarmItem("07:30","AM"))
        }

        val dailyAlarmRecycler = findViewById<RecyclerView>(R.id.recycler)
        dailyAlarmRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = DailyAlarmItemAdapter(context, items)
        }


    }//onCreate



}//MainActivity...


