package yosh.projects.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    var selectedDateText : TextView? = null
    var dateInMinutesText: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datePickerButton: Button = findViewById(R.id.datePickerBtn)
        selectedDateText = findViewById(R.id.selectedDateTv)
        dateInMinutesText = findViewById(R.id.calculatedDateTv)

        datePickerButton.setOnClickListener{
            datePickerEvent()
        }
    }

    private fun datePickerEvent() {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{ _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = "${selectedMonth+1}/$selectedDayOfMonth/$selectedYear"
                selectedDateText?.text = selectedDate
                val sdf = SimpleDateFormat("MM/dd/yy", Locale.US)
                val date = sdf.parse(selectedDate)
                date?.let{
                    val dateInMinutes = date.time / 3600000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time/3600000
                        val differenceInMinutes = currentDateInMinutes - dateInMinutes
                        dateInMinutesText?.text = differenceInMinutes.toString()
                    }

                }

            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000

        dpd.show()
    }
}



