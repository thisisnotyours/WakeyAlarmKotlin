package com.example.wakeyalarmkotlin.view.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.wakeyalarmkotlin.R
import com.example.wakeyalarmkotlin.view.model.DailyAlarmItem

class DailyAlarmItemAdapter(val context: Context, items: ArrayList<DailyAlarmItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    val items: ArrayList<DailyAlarmItem>
    var isClick: Boolean? = true
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val itemview: View = inflater.inflate(R.layout.recycler_time_item, parent, false)
        return VH(itemview)
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh = holder as VH
        val item: DailyAlarmItem = items[position]
        vh.titleText.setText(item.title)
        vh.timeText.setText(item.time)
        vh.timeTypeText.setText(item.timeType)
        vh.dateText.setText(item.date)
        //switch btn to boolean?

        vh.itemView.setOnLongClickListener {
            //showDeleteDialog()
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.dialog_delete_alarm, null)


            val alertDialog = AlertDialog.Builder(context)
                    .setTitle(context.getString(R.string.delete_alarm_dialog_title))
                    .setMessage(context.getString(R.string.delete_alarm_dialog))
                    .setPositiveButton(context.getString(R.string.delete)){
                        dialog, which ->
                        Toast.makeText(context, context.getString(R.string.delete_alarm_toast), Toast.LENGTH_SHORT).show()
                        //TODO: remove selected recyclerView item
                        items.remove(item)
                        notifyItemRemoved(position)
                       /* if (position > 0){
                            items.removeAt(position)
                            notifyDataSetChanged()
                            dialog.dismiss()
                        }*/
                    }
                    .setNegativeButton(context.getString(R.string.edit)){
                        dialog, _->
                        Toast.makeText(context, context.getString(R.string.edit), Toast.LENGTH_SHORT).show()
                        val v = inflater.inflate(R.layout.dialog_add_alarm, null)
                        val etTitle = v.findViewById<EditText>(R.id.et_title)
                        val etTime = v.findViewById<EditText>(R.id.et_time)
                        val etTimeType = v.findViewById<EditText>(R.id.et_time_type)
                        val tvDate = v.findViewById<TextView>(R.id.date_text)

                        val editDialog = AlertDialog.Builder(context)
                        editDialog.setView(v)
                        editDialog.setPositiveButton("OK"){
                            dialog, which ->
                            item.title = etTitle.text.toString()
                            item.time = etTime.text.toString()
                            item.timeType = etTimeType.text.toString()
                            notifyDataSetChanged()
                        }
                        editDialog.setNegativeButton("NO"){
                            dialog, which ->
                        }
                        editDialog.create()
                        editDialog.show()
                    }
                    .create()
            alertDialog.show()
            true
        }

    }



    override fun getItemCount(): Int {
        return items.size
    }



    inner class VH(itemview: View) : RecyclerView.ViewHolder(itemview){

        var titleText: TextView
        var timeText: TextView
        var timeTypeText: TextView
        var dateText: TextView
        var switchBtn: Switch

        init {
            titleText = itemview.findViewById(R.id.title_text)
            timeText = itemview.findViewById(R.id.time_text)
            timeTypeText = itemview.findViewById(R.id.time_type_text)
            dateText = itemview.findViewById(R.id.date_text)
            switchBtn = itemview.findViewById(R.id.switch_btn)

            //onClick on switch of each itemView
            switchBtn.setOnClickListener {
                if (isClick == true){   //if the click is true
                    //TODO:     How to bring string text in kotlin  ??????????
                    Toast.makeText(context, context.getString(R.string.alarm_on), Toast.LENGTH_LONG).show()
                    isClick = false     //실행 후 change to false
                }else{
                    Toast.makeText(context, context.getString(R.string.alarm_off), Toast.LENGTH_SHORT).show()
                    isClick = true      //실행 후 change to true
                }
            }

        }




    }


    /*private fun showDeleteDialog(){
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.dialog_delete_alarm, null)


        val alertDialog = AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.delete_alarm_dialog_title))
                .setMessage(context.getString(R.string.delete_alarm_dialog))
                .setPositiveButton(context.getString(R.string.delete)){
                    dialog, which ->
                    Toast.makeText(context, context.getString(R.string.delete_alarm_toast), Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton(context.getString(R.string.cancel), null)
                .create()

        //alertDialog.setView(view)
        //alertDialog.window.setLayout(500, 400)
        alertDialog.show()
    }*/




    //need to initialize.. is this a constructor?
    init {
        this.items = items
    }







}// TimeItemAdapter..