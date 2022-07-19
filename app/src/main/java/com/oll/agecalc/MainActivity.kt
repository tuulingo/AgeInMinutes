package com.oll.agecalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var dateTextView : TextView? = null
    private var minutesTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        dateTextView = findViewById(R.id.dateTextView)
        minutesTextView = findViewById(R.id.minutesTextView)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,{view, year, month, dayOfMonth ->

            val selectedDate = "$dayOfMonth/${month+1}/$year"
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDateinHours = sdf.parse(selectedDate).time / 3600000
            val currentDateinHours = sdf.parse(sdf.format(System.currentTimeMillis())).time / 3600000
            val differenceInHours = currentDateinHours - theDateinHours
            dateTextView?.text = selectedDate
            minutesTextView?.text = differenceInHours.toString()
        },
            year,
            month,
            day)
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }
}