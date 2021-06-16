package com.example.wakeyalarmkotlin.view.model

import org.w3c.dom.Text

data class DailyAlarmItem (
    var title: String,
    var time: String,
    var timeType: String,
    var date: String
)