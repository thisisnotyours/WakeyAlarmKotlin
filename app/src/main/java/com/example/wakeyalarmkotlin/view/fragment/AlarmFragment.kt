package com.example.wakeyalarmkotlin.view.fragment

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.wakeyalarmkotlin.R
import com.example.wakeyalarmkotlin.view.view.MainActivity
import java.text.SimpleDateFormat
import java.util.*

class AlarmFragment(fragmentAlarm: Int) : Fragment(fragmentAlarm) {
    var alarmTitle: EditText? = null
    var isClicked: Boolean = true
    var cal = Calendar.getInstance()
    var dateText: TextView? = null
    var dateTypeText: TextView? = null

    var monClicked: Boolean = true
    var tueClicked: Boolean = true
    var wedClicked: Boolean = true
    var thurClicked: Boolean = true
    var friClicked: Boolean = true
    var satClicked: Boolean = true
    var sunClicked: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.fragment_alarm, container, false)
        //return inflater.inflate(R.layout.fragment_alarm, container, false)


        alarmTitle= view?.findViewById<EditText>(R.id.et_alarm_title)
        alarmTitle?.setOnClickListener {
            // 알람 제목줄 클릭했을 때
            // 바텀 네비게이션 뷰 안보이게 하거나
            // 키보드 패드 사이즈 줄이기
            // 아니면 바텀 네비게이션을 appBar 위로 만들기

            // 입력 후 키보드 내리기
        }

        //TODO: set current date ********* --> and then change
        dateTypeText = rootView.findViewById(R.id.tv_date_type)


        dateText = rootView.findViewById(R.id.tv_date)
        val calendarIcon = rootView.findViewById<ImageView>(R.id.iv_calendar)
        val mon = rootView.findViewById<Button>(R.id.mon)
        val tue = rootView.findViewById<Button>(R.id.tue)
        val wed = rootView.findViewById<Button>(R.id.wed)
        val thu = rootView.findViewById<Button>(R.id.thu)
        val fri = rootView.findViewById<Button>(R.id.fri)
        val sat = rootView.findViewById<Button>(R.id.sat)
        val sun = rootView.findViewById<Button>(R.id.sun)
        val cancelBtn = rootView.findViewById<Button>(R.id.cancel_btn)
        var saveBtn = rootView.findViewById<Button>(R.id.save_btn)

        /* create an OnDateSetListener */
        val dateSetListener = object : DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                Toast.makeText(context, "${cal.get(Calendar.YEAR)}+${cal.get(Calendar.MONTH)}+${cal.get(Calendar.DAY_OF_MONTH)}", Toast.LENGTH_SHORT).show()
                updateDateInView()
            }

        }

        // When you click on the button, show DatePickerDialog that is set with OnDateSetListener
        /* Calendar imageView onClick */
        calendarIcon!!.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                context?.let {
                    DatePickerDialog(it
                            , dateSetListener
                            , cal.get(Calendar.YEAR)
                            , cal.get(Calendar.MONTH)
                            , cal.get(Calendar.DAY_OF_MONTH)).show()
                }

            }

        })



        mon.setOnClickListener {
            if (isClicked == true){
                Log.d("sss","ssssss")
                mon.setBackgroundResource(R.drawable.selected_btn_app_color)
                mon.setTextColor(resources.getColor(R.color.white))
                isClicked = false
            }else{
                Log.d("ttt","ttttttt")
                mon.setBackgroundResource(R.drawable.unselected_btn_white)
                mon.setTextColor(resources.getColor(R.color.background_dark))
                isClicked = true
            }
        }


        tue.setOnClickListener {
            if (isClicked == true){
                Log.d("sss","ssssss")
                tue.setBackgroundResource(R.drawable.selected_btn_app_color)
                tue.setTextColor(resources.getColor(R.color.white))
                isClicked = false
            }else{
                Log.d("ttt","ttttttt")
                tue.setBackgroundResource(R.drawable.unselected_btn_white)
                tue.setTextColor(resources.getColor(R.color.background_dark))
                isClicked = true
            }
        }


        wed.setOnClickListener {
            if (isClicked == true){
                wed.setBackgroundResource(R.drawable.selected_btn_app_color)
                wed.setTextColor(resources.getColor(R.color.white))
                isClicked = false
            }else{
                wed.setBackgroundResource(R.drawable.unselected_btn_white)
                wed.setTextColor(resources.getColor(R.color.background_dark))
                isClicked = true
            }
        }


        thu.setOnClickListener {
            if (isClicked == true){
                thu.setBackgroundResource(R.drawable.selected_btn_app_color)
                thu.setTextColor(resources.getColor(R.color.white))
                isClicked = false
            }else{
                thu.setBackgroundResource(R.drawable.unselected_btn_white)
                thu.setTextColor(resources.getColor(R.color.background_dark))
                isClicked = true
            }
        }


        fri.setOnClickListener {
            if (isClicked == true){
                fri.setBackgroundResource(R.drawable.selected_btn_app_color)
                fri.setTextColor(resources.getColor(R.color.white))
                isClicked = false
            }else{
                fri.setBackgroundResource(R.drawable.unselected_btn_white)
                fri.setTextColor(resources.getColor(R.color.background_dark))
                isClicked = true
            }
        }


        sat.setOnClickListener {
            if (isClicked == true){
                sat.setBackgroundResource(R.drawable.selected_btn_app_color)
                sat.setTextColor(resources.getColor(R.color.white))
                isClicked = false
            }else{
                sat.setBackgroundResource(R.drawable.unselected_btn_white)
                sat.setTextColor(resources.getColor(R.color.app_color))
                isClicked = true
            }
        }


        sun.setOnClickListener {
            if (isClicked == true){
                sun.setBackgroundResource(R.drawable.selected_btn_app_color_peach)
                sun.setTextColor(resources.getColor(R.color.white))
                isClicked = false
            }else{
                sun.setBackgroundResource(R.drawable.unselected_btn_white)
                sun.setTextColor(resources.getColor(R.color.red))
                isClicked = true
            }
        }


        cancelBtn.setOnClickListener {
            Toast.makeText(context, "${getString(R.string.canceled)}", Toast.LENGTH_SHORT).show()
            // TODO: do nothing
            // clear whats saved.
        }


        saveBtn.setOnClickListener {
            Toast.makeText(context, "${getString(R.string.saved)}", Toast.LENGTH_SHORT).show()
            // TODO: save contents
            val intent = Intent(context, MainActivity::class.java)
            //send title, time, time type, date
            // or save these data in SQLite
            startActivity(intent)
        }

        return rootView

    }//onCreateView



    private fun updateDateInView(){
        val mFormat = "MM.dd.yy"
        val sdf = SimpleDateFormat(mFormat, Locale.US)
       // if (sdf.format(cal.timeInMillis) == )
       // if (cal.timeInMillis == )
        dateText!!.text = sdf.format(cal.timeInMillis)
        Log.d("dateTest>", dateText.toString())
    }




}//AlarmFragment