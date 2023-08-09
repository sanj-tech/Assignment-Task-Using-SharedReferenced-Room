package com.example.assignmentsnapworktask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.assignmentsnapworktask.dao.DateInfoInterface
import com.example.assignmentsnapworktask.databinding.ActivitySecondBinding
import com.example.assignmentsnapworktask.model.DateInfo
import kotlinx.coroutines.launch

class SecondActivity : AppCompatActivity() {
    private lateinit var selectedDate: String
    lateinit var binding: ActivitySecondBinding
    private lateinit var dateInfoInterface: DateInfoInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_second
        )


        selectedDate = intent.getStringExtra("selectedDate") ?: ""
        val selectedMonth = intent.getIntExtra("selectedMonth", 1)
        val selectedYear = intent.getIntExtra("selectedYear", 1980)

        val formattedDate = "$selectedYear-$selectedMonth-$selectedDate"
        binding.dateTextView.text = "Selected Date: $formattedDate"

        dateInfoInterface =
            com.example.assignmentsnapworktask.db.DataInfoDB.getInstance(this).dateInfoInterface()

        lifecycleScope.launch {

            val savedDateInfo = dateInfoInterface.getTextByDate(formattedDate)
            if (savedDateInfo != null && !savedDateInfo.text.isNullOrEmpty()) {
                binding.inputEditText.setText(savedDateInfo.text)
            }
        }


        binding.submitButton.setOnClickListener {
            val inputText = binding.inputEditText.text.toString()
            if (inputText.isNotEmpty()) {

                val entry = DateInfo(0, date = formattedDate, text = inputText)
                saveText(entry)
                binding.inputEditText.text.clear()
            }

        }

    }

    private fun saveText(dateInfo: DateInfo) {
        lifecycleScope.launch {
            dateInfoInterface.insert(dateInfo)
        }
    }
}

//Data save using SharedPreferences

//        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
//
//        selectedDate = intent.getStringExtra("selectedDate") ?: ""
//        binding.dateTextView.text = "Selected Date: $selectedDate"
//
//        val savedInput = sharedPreferences.getString(INPUT_KEY_PREFIX + selectedDate, "")
//        binding.inputEditText.setText(savedInput)
//
//        val allSavedData = retrieveInputForDate(this, selectedDate)
//        binding.tvDisplay.text = allSavedData
//
//        binding.submitButton.setOnClickListener {
//            val userInput = binding.inputEditText.text.toString()
//            saveInputToPreferences(selectedDate, userInput)
//        }
//    }
//
//
//    private fun saveInputToPreferences(date: String, input: String) {
//        val editor = sharedPreferences.edit()
//        editor.putString(INPUT_KEY_PREFIX + date, input)
//        editor.apply()
//
//
//    }
//
//    fun retrieveInputForDate(context: Context, date: String): String {
//        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
//        val allData = StringBuilder()
//
//        // Iterate through the keys and retrieve values
//        val allKeys = sharedPreferences.all.keys
//        for (key in allKeys) {
//            if (key.startsWith(INPUT_KEY_PREFIX)) {
//                val date = key.removePrefix(INPUT_KEY_PREFIX)
//                val input = sharedPreferences.getString(key, "") ?: ""
//                allData.append("Date: $date, $input\n")
//            }
//        }
//
//        return allData.toString()
//

//}
    


