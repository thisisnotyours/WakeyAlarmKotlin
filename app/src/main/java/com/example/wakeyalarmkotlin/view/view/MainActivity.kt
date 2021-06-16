package com.example.wakeyalarmkotlin.view.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wakeyalarmkotlin.R
import com.example.wakeyalarmkotlin.view.adapter.DailyAlarmItemAdapter
import com.example.wakeyalarmkotlin.view.model.DailyAlarmItem
import com.google.android.material.navigation.NavigationView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var dailyAlarmRecycler: RecyclerView
    private lateinit var items: ArrayList<DailyAlarmItem>
    private lateinit var dailyAlarmAdapter: DailyAlarmItemAdapter
    private lateinit var context: Context
    private lateinit var yourAlarmText: TextView
    private lateinit var titles : String
    private lateinit var times : String
    private lateinit var timeTypes : String
    private lateinit var dates : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        context = this

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

        var cnt = Int

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


        //TODO: set RecycleView with Adapter
        items = ArrayList()
        dailyAlarmRecycler = findViewById(R.id.recycler)
        dailyAlarmAdapter = DailyAlarmItemAdapter(this, items)
        dailyAlarmRecycler.layoutManager = LinearLayoutManager(this)
        dailyAlarmRecycler.adapter = dailyAlarmAdapter


        yourAlarmText = findViewById(R.id.tv_your_alarm)
        if (items.size != null){
            yourAlarmText.isVisible = true
        }else{
            yourAlarmText.isVisible = false
        }

/**
        // create recyclerView -> need to make it dynamic
        val items = arrayListOf<DailyAlarmItem>()
        /*for (i in 0..2){
            items.add(DailyAlarmItem("School", "07:30","AM" ))
        }*/


        val dailyAlarmRecycler = findViewById<RecyclerView>(R.id.recycler)
        dailyAlarmRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = DailyAlarmItemAdapter(context, items)
        }
**/

    }//onCreate





    /* click on FAB */
    fun addAlarm(view: View) {
        //transfer to addingPage
        //TODO: can apply animation when clicking?
        /*val intent = Intent(this, AddAlarmActivity::class.java)
        startActivity(intent)*/

        //TODO: show Popup Menu here & add RecyclerView
        addDailyAlarmInfo()

    }




   /* set Dialog */
    private fun addDailyAlarmInfo(){
       val inflater = LayoutInflater.from(this)
       val v = inflater.inflate(R.layout.dialog_add_alarm, null)
      /* * set view **/
       val etTitle = v.findViewById<EditText>(R.id.et_title)
       val etTime = v.findViewById<EditText>(R.id.et_time)
       val etTimeType = v.findViewById<EditText>(R.id.et_time_type)
       val etDate = v.findViewById<EditText>(R.id.et_date)
       val addDialog = AlertDialog.Builder(this, R.style.MyDialogTheme)
       addDialog.setView(v)
       addDialog.setPositiveButton(context.getString(R.string.ok)){
           dialog, which ->
           titles = etTitle.text.toString()
           times = etTime.text.toString()
           timeTypes = etTimeType.text.toString()
           dates = etDate.text.toString()
           System.out.println("timecheck:  "+etTime.text.toString());
           if (etTime.text.toString() == ""){
               Toast.makeText(this, context.getString(R.string.set_time), Toast.LENGTH_SHORT).show()
               //TODO: How to remain dialog not to dismiss?
           }else{
               items.add(DailyAlarmItem("$titles", "$times", "$timeTypes","$dates"))
               dailyAlarmAdapter.notifyDataSetChanged()
               Toast.makeText(this, context.getString(R.string.alarm_saved) , Toast.LENGTH_SHORT).show()
               dialog.dismiss()
           }

       }
       addDialog.setNegativeButton(context.getString(R.string.cancel)){
           dialog, _->
           dialog.dismiss()
           Toast.makeText(this, context.getString(R.string.canceled), Toast.LENGTH_SHORT).show()
       }
       addDialog.create()
       addDialog.show()


       //al OkBtn: Button = addDialog.button
    }

    fun addAlarmPage(view: View) {
        val intent = Intent(this, MainAlarmActivity::class.java)
        startActivity(intent)
    }


}//MainActivity...








