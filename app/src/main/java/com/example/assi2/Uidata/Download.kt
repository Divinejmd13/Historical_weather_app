package com.example.assi2.Uidata

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.assi2.Api.Resource
import com.example.assi2.Api.WeatherViewModel
import com.example.assi2.Database.HistoricalWeather
import com.example.assi2.Database.HistoricalWeatherDao
import com.example.assi2.Database.WeatherDatabase
import com.example.assi2.ui.theme.Assi2Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.nio.file.WatchEvent
class Download {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun DownloadScreen(modifier: Modifier, viewModel: WeatherViewModel, navController: NavController) {
        val weatherData by viewModel.weatherData.observeAsState(initial = Resource.Loading())
        var clickeddatasearch by remember {
            mutableStateOf(false)
        }
        var showDialog by remember { mutableStateOf(false) }
        var data by remember { mutableStateOf<List<HistoricalWeather>>(emptyList()) }

        // Function to fetch weather data
        LaunchedEffect(Unit) {
            data = withContext(Dispatchers.IO) {
                viewModel.getAllWeather()
            }
        }

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    containerColor = Color(0xFF02C650),
                    contentColor = Color.White,
                    onClick = { showDialog = true }
                ) {
                    Icon(Icons.Filled.Delete, "Floating action button.", tint = Color.White)
                }
            },
            containerColor = Color(0xFF1E2122),
            topBar = {
                CenterAlignedTopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Localized description",
                                tint = Color.White
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { navController.navigate("search") }) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Localized description",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF242B33),
                        titleContentColor = Color.White,
                    ),
                    title = {
                        Text("Downloaded Data")
                    }
                )
            },
        ) {
            Column(modifier = modifier.padding(it)) {
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
                                    viewModel.jaga_ka_offline_naam=weather.location
                                    clickeddatasearch=true
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
            }
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

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Delete All Weather Data") },
                text = { Text("Are you sure you want to delete all weather data?") },
                confirmButton = {
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF02C650)),
                        onClick = {
                            viewModel.deleteAllWeatherData()
                            data= emptyList()

                            showDialog = false

                        }
                    ) { Text("Yes") }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) { Text("No") }
                }
            )
        }
    }
}
