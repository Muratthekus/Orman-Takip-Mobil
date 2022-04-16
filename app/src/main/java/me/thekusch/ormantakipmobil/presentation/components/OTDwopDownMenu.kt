package me.thekusch.ormantakipmobil.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.toSize

@Composable
fun OTDropDownMenu(
    mList: List<String>?,
    label: String,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedText by remember {
        mutableStateOf(mList?.getOrNull(0))
    }
    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }
    val icon = if (expanded)
        Icons.Filled.ArrowDropUp
    else
        Icons.Filled.ArrowDropDown

    Column {
        OutlinedTextField(
            value = selectedText ?: "",
            onValueChange = { selectedText = it },
            readOnly = true,
            modifier = modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            label = {
                Text(text = label)
            },
            trailingIcon = {
                Icon(
                    icon,
                    contentDescription = "arrowIcon",
                    modifier = Modifier.clickable {
                        expanded = !expanded
                    }
                )
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            mList?.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        selectedText = item
                        onItemSelected(item)
                        expanded = false
                    }) {
                    Text(text = item)
                }
            }
        }
    }
}

@Preview
@Composable
fun previewOTDropDownMenu(
    mList: List<String> = listOf("asf","agfasg","asfasf"),
    label: String = "DENEME"
) {
    OTDropDownMenu(mList = mList, label = label, {} )
}