package com.example.assi2.Uidata

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.shapes.Shape
import android.location.Address
import android.location.Geocoder
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.assi2.Api.Resource
import com.example.assi2.Api.WeatherViewModel
import com.example.assi2.Database.HistoricalWeather
import com.example.assi2.Database.HistoricalWeatherDao
import com.example.assi2.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

import androidx.compose.runtime.*
import java.util.Calendar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "InvalidColorHexValue")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, context: Context,viewModel: WeatherViewModel,navController:NavController,net:Boolean,asses:Boolean) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val weatherData by viewModel.weatherData.observeAsState(initial = Resource.Loading())

    val geocoder = Geocoder(context)
    var showBottomSheet2 by remember { mutableStateOf(false) }
    val sheetState2 = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(asses) }
    var clickeddatasearch by remember {
        mutableStateOf(false)
    }
    var  isAfterYesterday by remember {
        mutableStateOf(false)
    }
    var clickeddatasearch2 by remember {
        mutableStateOf(false)
    }
    Scaffold { contentPadding ->
        // Screen content
        Column(modifier = Modifier.fillMaxSize()) {
            Column(modifier = modifier
                .fillMaxWidth()
                .requiredHeight(150.dp)) {
                Image(
                    modifier = modifier.align(Alignment.CenterHorizontally),
                    painter = painterResource(id = R.drawable.designer),
                    contentDescription = "Background"
                )
            }

            // Search Weather text
            Text(
                text = "Search Weather",
                modifier = modifier
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.SemiBold
            )

            // Additional text
            Text(
                text = "Enter date to retrieve temperature",
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 0.dp),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground // Light gray color
            )

            Image(
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 10.dp),
                painter = painterResource(id = R.drawable.bg2),
                contentDescription = "Background"
            )

            ElevatedButton(onClick = {
                if(net){
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet = true
                        }
                    }


                }else{

                    scope.launch { sheetState2.hide() }.invokeOnCompletion {
                        if (!sheetState2.isVisible) {
                            showBottomSheet2 = true
                        }
                    }
                }
            },
                modifier= modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF02C650),
                    contentColor= Color(0xFFFFFFFF) ,
                    disabledContainerColor= Color.Gray,
                    disabledContentColor= Color.Unspecified,
              )
            ) {
                Text("Get Started",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyLarge)
            }
        }


        if(showBottomSheet2){
            ModalBottomSheet(

                modifier=modifier.fillMaxSize(),
                containerColor = Color(0xFF242B33),
                onDismissRequest = {
                    showBottomSheet2 = false
                },
                sheetState = sheetState2
            ) {
                var data by remember { mutableStateOf<List<HistoricalWeather>>(emptyList()) }

                LaunchedEffect(Unit) {
                    data = withContext(Dispatchers.IO) {
                       viewModel.getAllWeather()
                    }
                }

                Column(modifier = Modifier.padding(10.dp)) {
                    Row(modifier.padding(15.dp)){
                        Text(
                            text = "Downloaded data",
                            modifier = modifier,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFFFFFFFF)
                        )
                    }

                    LazyColumn(modifier = Modifier.padding(15.dp)) {
                        items(data) { weather ->
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF37474F),
                                tonalElevation = 2.dp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        viewModel.fetchDataFromDatabase(weather.id)
                                        viewModel.date = weather.date
                                        viewModel.jaga_ka_offline_naam = weather.location
                                        clickeddatasearch2 = true
                                    }

                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = "Location: ${weather.location}",
                                        color = Color.White,
                                        style = MaterialTheme.typography.bodyLarge,
                                        modifier = Modifier.padding(bottom = 4.dp),
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Text(
                                        text = "Date: ${weather.date}",
                                        color = Color.White,
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = FontWeight.SemiBold,
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                    if(clickeddatasearch2){
                        Crossfade(targetState = weatherData, label = "") { state ->
                            when (state) {
                                is Resource.Loading -> {
                                    Column(
                                        modifier = Modifier
                                            .padding(horizontal = 10.dp)
                                            .size(25.dp)
                                    ) {
                                        CircularProgressIndicator(
                                            color = Color.White
                                        )
                                    }
                                }
                                is Resource.Success -> {
                                    // Assuming you have a composable function called `data` to display weather data
                                    Column(
                                        modifier = Modifier
                                            .padding(horizontal = 10.dp)
                                    ) {
                                        Text(
                                            text = "\u2713", // Unicode for tick mark
                                            fontSize = 20.sp,
                                            color = Color.White,
                                            modifier = Modifier
                                                .animateContentSize()
                                        )
                                        LaunchedEffect(Unit ){

                                            delay(1000L)
                                            showBottomSheet2 = false
                                            navController.navigate("Second")

                                        }
                                    }
                                }
                                is Resource.Error -> {
                                    Text(state.message ?: "Unknown error", color = Color.Red)
                                }
                            }
                        }

                    }
            }

         }
        }
        if (showBottomSheet) {
            ModalBottomSheet(
                containerColor = Color(0xFF242B33),
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                val state = rememberDatePickerState()
                val openDialog = remember { mutableStateOf(false) }
                var date = remember { mutableStateOf("Select date") }
                var input by remember { mutableStateOf("") }
                var addresslist by remember { mutableStateOf<List<Address>>(emptyList()) } // Initialize with an empty list
                var address by remember { mutableStateOf<Address?>(null) }
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

                var insertionCompleted by remember {
                    mutableStateOf(false)
                }
                var checked = remember { mutableStateOf(false) }
                Column (modifier = modifier

                    .padding(15.dp)
                    .fillMaxSize()){

                    Row(modifier.padding(15.dp)){
                        Text(
                            text = "Access Data",
                            modifier = modifier,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFFFFFFFF)
                        )
                    }

                    Column(modifier.padding(vertical = 20.dp)){
                        OutlinedTextField(
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Color.White,
                                focusedBorderColor = Color(0xFF02C650),
                                focusedLabelColor = Color.LightGray,
                                unfocusedLabelColor = Color.Gray,
                                focusedTextColor = Color.White
                            ),
                            modifier = modifier
                                .align(Alignment.CenterHorizontally)
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            value = input,
                            onValueChange = { newText ->
                                input = newText
                                if (newText.isNotEmpty()) {
                                    addresslist = runCatching {
                                        geocoder.getFromLocationName(newText, 5) ?: emptyList()
                                    }.getOrElse { emptyList() }
                                } else {
                                    // Clear the address list when input is empty
                                    addresslist = emptyList()
                                }
                            },
                            label = { Text(text = "Enter the Location") },
                            shape = RoundedCornerShape(15.dp), // Add rounded corners with a radius of 16.dp
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                        )


                        if (addresslist.isNotEmpty()) {
                            LazyColumn(modifier.padding(horizontal = 20.dp)) {
                                items(addresslist) { item ->
                                    Row(
                                        horizontalArrangement = Arrangement.Start,
                                        modifier = modifier
                                            .padding(5.dp)
                                            .fillMaxWidth()
                                            .clickable {
                                                address = item
                                                addresslist = emptyList()
                                                val uniqueParts = setOfNotNull(
                                                    address?.featureName,
                                                    address?.locality,
                                                    address?.countryName
                                                )
                                                input = uniqueParts.joinToString(separator = " ")
                                            }

                                    ) {

                                        Column(modifier = modifier.padding(horizontal = 2.dp)) {
                                            Text(
                                                text = item.locality ?: "",
                                                color = Color.White
                                            )
                                        }

                                        if (item.locality != item.featureName) {
                                            Column(modifier = modifier.padding(horizontal = 2.dp)) {
                                                Text(
                                                    text = item.featureName ?: "",
                                                    color = Color.White
                                                )
                                            }
                                        }
                                        Column(modifier = modifier.padding(horizontal = 2.dp)) {
                                            Text(
                                                text = item.countryName ?: "",
                                                color = Color.White
                                            )
                                        }

                                    }
                                    Divider(color = Color(0xFF02C650), thickness = 1.dp, modifier = modifier.padding(10.dp))

                                }


                            }

                        }
                    }
                    ElevatedButton(onClick = {
                        openDialog.value=true
                    },
                        modifier= modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 15.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF02C650),
                            contentColor= Color(0xFFFFFFFF) ,
                            disabledContainerColor= Color.Gray,
                            disabledContentColor= Color.Unspecified,

                            )
                    ) {
                        Icon(Icons.Default.DateRange, contentDescription ="Calender",modifier.padding(horizontal = 10.dp))
                        Text(text =date.value,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.bodyLarge)
                    }
                    Column() {
                        Row(modifier.padding(15.dp)){
                            Checkbox(
                                checked = checked.value,
                                onCheckedChange = { isChecked -> checked.value = isChecked }
                            )
                            Text("Download data Offline"
                                ,color= Color.White,modifier=modifier.padding(vertical = 13.dp))
                        }
                    }
                    Row(modifier = modifier.align(Alignment.CenterHorizontally)){

                        ElevatedButton(onClick = {
                            if(!isAfterYesterday) {
                                address?.let {
                                    viewModel.fetchWeatherData(
                                        latitude = it.latitude,
                                        longitude = it.longitude,
                                        date.value,
                                        date.value,
                                        address = address
                                    )
                                }
                                viewModel.date = date.value
                            }else{
                                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // Get today's date
                                val today = Calendar.getInstance()
                                today.add(Calendar.DATE, -2)
                                val todayDate = dateFormat.format(today.time) // Get the date 10 days ago
                                val tenDaysAgo = Calendar.getInstance()
                                tenDaysAgo.add(Calendar.DATE, -3560)
                                val tenDaysAgoDate = dateFormat.format(tenDaysAgo.time)
                                address?.let {
                                    viewModel.fetchWeatherData(
                                        latitude = it.latitude,
                                        longitude = it.longitude,
                                        tenDaysAgoDate,
                                        todayDate,

                                        address = address
                                    )
                                }
                                viewModel.date = "$tenDaysAgoDate to $todayDate"
                            }
                            clickeddatasearch=true

                        },
                            modifier = modifier

                                .padding(vertical =0.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF02C650),
                                contentColor = Color(0xFFFFFFFF),
                                disabledContainerColor = Color.Gray,
                                disabledContentColor = Color.Unspecified,

                                )
                        ) {
                            if(clickeddatasearch){
                                Crossfade(targetState = weatherData, label = "") { state ->
                                    when (state) {
                                        is Resource.Loading -> {
                                            Column(
                                                modifier = Modifier
                                                    .padding(horizontal = 10.dp)
                                                    .size(25.dp)
                                            ) {
                                                CircularProgressIndicator(
                                                    color = Color.White
                                                )
                                            }
                                        }
                                        is Resource.Success -> {
                                            // Assuming you have a composable function called `data` to display weather data
                                            Column(
                                                modifier = Modifier
                                                    .padding(horizontal = 10.dp)
                                            ) {
                                                Text(
                                                    text = "\u2713", // Unicode for tick mark
                                                    fontSize = 20.sp,
                                                    color = Color.White,
                                                    modifier = Modifier
                                                        .animateContentSize()
                                                )
                                               LaunchedEffect(Unit ){

                                                   delay(1000L)
                                                   showBottomSheet=false
                                                   navController.navigate("Second")

                                                   if (!insertionCompleted && checked.value && weatherData.data!=null) {
                                                       weatherData.data?.let { viewModel.insertWeatherDataIntoDB(weatherResponse = it) }
                                                       insertionCompleted = true
                                                   }

                                               }
                                            }
                                        }
                                        is Resource.Error -> {
                                            Text(state.message ?: "Unknown error", color = Color.Red)
                                        }
                                    }
                                }

                            }
                            Text(

                                text = "⛅ Search 🌦️",
                                fontWeight = FontWeight.SemiBold,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }


                    }
                    if (openDialog.value) {
                        DatePickerDialog(
                            onDismissRequest = {
                                openDialog.value = false
                            },
                            confirmButton = {
                                TextButton(
                                    onClick = {
                                        state.selectedDateMillis?.let { timestamp ->
                                            val selectedDate = Date(timestamp)
                                            val yesterday = Calendar.getInstance()
                                            yesterday.add(Calendar.DATE, -2) // Subtract 1 day to get yesterday's date
                                            isAfterYesterday = selectedDate.after(yesterday.time)


                                            // The selected date is after yesterday
                                            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                                            val formattedDate = dateFormat.format(selectedDate)
                                            date.value = formattedDate

                                        }

                                        openDialog.value = false
                                    }
                                ) {
                                    Text("OK")
                                }
                            },
                            dismissButton = {
                                TextButton(
                                    onClick = {
                                        openDialog.value = false
                                    }
                                ) {
                                    Text("CANCEL")
                                }
                            }
                        ) {

                            DatePicker(
                                colors = DatePickerDefaults.colors(
                                    containerColor = Color.Gray,
                                    titleContentColor = Color(0xFF000000), // Green theme
                                    headlineContentColor = Color(0xFF02C650), // Green theme
                                    weekdayContentColor = Color(0xFF027A32), // Green theme
                                    subheadContentColor = Color(0xFFFF5722), // Green theme
                                    yearContentColor = Color(0xFF02C650), // Green theme
                                    currentYearContentColor = Color(0xFF02C650), // Green theme
                                    selectedYearContentColor = Color(0xFF02C650), // Green theme
                                    selectedYearContainerColor = Color(0xFF02C650), // Green theme
                                    dayContentColor = Color(0xFF02C650), // Green theme
                                    disabledDayContentColor = Color(0xFF02C650), // Green theme
                                    disabledSelectedDayContentColor = Color(0xFF02C650), // Green theme
                                    selectedDayContainerColor = Color(0xFF02C650), // Green theme
                                    disabledSelectedDayContainerColor = Color(0xFF02C650), // Green theme
                                    todayContentColor = Color(0xFF02C650), // Green theme
                                    todayDateBorderColor = Color(0xFF02C650), // Green theme
                                    dayInSelectionRangeContentColor = Color(0xFF02C650), // Green theme
                                    dayInSelectionRangeContainerColor = Color(0xFF02C650), // Green them
                                ),
                                state = state,

                                )
                        }
                    }


                }
            }
        }
    }
}