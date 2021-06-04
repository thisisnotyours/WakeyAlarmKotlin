package com.example.wakeyalarmkotlin.view.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.wakeyalarmkotlin.R
import com.example.wakeyalarmkotlin.view.value.DailyAlarmItem

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
        vh.timeText.setText(item.time)
        vh.timeTypeText.setText(item.timeType)
        //switch btn to boolean?

    }



    override fun getItemCount(): Int {
        return items.size
    }



    inner class VH(itemview: View) : RecyclerView.ViewHolder(itemview){
        var timeText: TextView
        var timeTypeText: TextView
        var switchBtn: Switch

        init {
            timeText = itemview.findViewById(R.id.time_text)
            timeTypeText = itemview.findViewById(R.id.time_type_text)
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




            //onClick on each itemView
            itemview.setOnClickListener{
                Toast.makeText(context, "transfer to alarm setting page/ show dialog to transfer", Toast.LENGTH_SHORT).show()
            }



            itemview.setOnLongClickListener {
                // show dialog to delete this item or not
                showDeleteDialog()
                true
            }


        }

        private fun showDeleteDialog(){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.dialog_delete_alarm, null)


            val alertDialog = AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.delete_alarm_dialog_title))
                .setMessage(context.getString(R.string.delete_alarm_dialog))
                .setPositiveButton(context.getString(R.string.delete)){
                    dialog, which ->
                }
                .setNegativeButton(context.getString(R.string.cancel), null)
                .create()

            //alertDialog.setView(view)
            //alertDialog.window.setLayout(500, 400)
            alertDialog.show()
        }

    }




    //need to initialize.. is this a constructor?
    init {
        this.items = items
    }







}// TimeItemAdapter..