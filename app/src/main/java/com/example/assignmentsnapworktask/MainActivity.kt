package com.example.assignmentsnapworktask

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.assignmentsnapworktask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        // Populate month spinner
        val months = resources.getStringArray(R.array.months)
        val monthAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, months)
        binding.monthSpinner.adapter = monthAdapter

        // Populate year spinner
        val years = (1980..2040).toList().map { it.toString() }
        val yearAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)
        binding.yearSpinner.adapter = yearAdapter

        binding.showDatesButton.setOnClickListener {
            val selectedMonth = binding.monthSpinner.selectedItemPosition + 1
            val selectedYear = years[binding.yearSpinner.selectedItemPosition].toInt()

            // Calculate the number of days in the selected month
            val numDays = when (selectedMonth) {
                2 -> if (selectedYear % 4 == 0 && (selectedYear % 100 != 0 || selectedYear % 400 == 0)) 29 else 28 4, 6, 9, 11 -> 30
                else -> 31
            }

            // Populate date list view
            val dates = (1..numDays).toList().map { it.toString() }
            val dateAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dates)
            binding.dateListView.adapter = dateAdapter
        }

        binding.dateListView.setOnItemClickListener { _, _, position, _ ->
            val selectedMonth = binding.monthSpinner.selectedItemPosition + 1
            val selectedYear = binding.yearSpinner.selectedItem.toString().toInt()

            val selectedDate = binding.dateListView.getItemAtPosition(position) as String
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("selectedDate", selectedDate)
            intent.putExtra("selectedMonth", selectedMonth)
            intent.putExtra("selectedYear", selectedYear)
            startActivity(intent)
        }
    }
}
