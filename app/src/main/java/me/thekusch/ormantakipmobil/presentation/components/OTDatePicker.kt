package me.thekusch.ormantakipmobil.presentation.components

import android.text.format.DateFormat
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.google.android.material.datepicker.MaterialDatePicker

@Composable
fun OTDatePicker(
    activity: AppCompatActivity,
    defaultDate: String? = null,
    label: String,
    onItemSelected: (date: String?) -> Unit
) {
    var selectedDate by remember {
        mutableStateOf(defaultDate)
    }

    OutlinedTextField(
        value = selectedDate ?: "",
        onValueChange = {
            selectedDate = it
        },
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(text = label)
        },
        readOnly = true,
        trailingIcon = {
            Icon(
                Icons.Filled.DateRange,
                contentDescription = "dateIcon",
                modifier = Modifier.clickable {
                    val picker = MaterialDatePicker.Builder.datePicker().build()
                    picker.show(activity.supportFragmentManager, picker.toString())
                    picker.addOnPositiveButtonClickListener {
                        selectedDate = DateFormat.format("yyyy/MM/dd", it).toString()
                        onItemSelected(selectedDate)
                    }
                }
            )
        })
}
