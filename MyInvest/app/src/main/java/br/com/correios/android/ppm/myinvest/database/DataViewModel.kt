package br.com.correios.android.ppm.myinvest.database
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class DataViewModel:ViewModel() {
    private val _data = MutableLiveData("")
    var time: LiveData<String> = _data

    fun selectDateTime(context: Context) {
        var data = ""
        val currentDateTime = Calendar.getInstance()
        val startYear = currentDateTime.get(Calendar.YEAR)
        val startMonth = currentDateTime.get(Calendar.MONTH)
        val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(context, { _, year, month, day ->

                val pickedDateTime = Calendar.getInstance()
                pickedDateTime.set(year, month, day)
                val monthStr: String
                if ((month + 1).toString().length == 1) {
                    monthStr = "0${month + 1}"
                } else {
                    monthStr = month.toString()
                }
                data = "$day - $monthStr - $year "
                data = getFormattedDate(pickedDateTime.time, "dd MMM,yyy")
                updateDate(data)

        }, startYear, startMonth, startDay).show()
    }
    private fun updateDate(date: String) {
        _data.value = date
    }
}
@SuppressLint("SimpleDateFormat")
fun getFormattedDate(date: Date?, format: String): String {
    try {
        if (date != null) {
            val formatter = SimpleDateFormat("dd-MM-yyyy")
            return formatter.format(date)
        }
    } catch (e: Exception) {

    }
    return ""
}