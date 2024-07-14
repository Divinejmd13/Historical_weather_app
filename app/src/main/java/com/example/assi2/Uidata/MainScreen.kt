package com.example.assi2.Uidata

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.assi2.Api.HistoricalWeatherResponse
import com.example.assi2.Api.Resource
import com.example.assi2.Api.WeatherViewModel
import com.example.assi2.R

class MainScreen {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "DiscouragedApi",
        "SuspiciousIndentation"
    )
    @Composable
    fun ShowScreen(modifier: Modifier, viewModel: WeatherViewModel,navController: NavController) {
        Scaffold(
            containerColor = Color(0xFF1E2122),
            topBar = {

                CenterAlignedTopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate("Download") }) {
                            Icon(
                                painter = painterResource(id = R.drawable.download),
                                contentDescription = "Localized description",
                                tint = Color.White,
                                modifier = modifier.size(30.dp)
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { navController.navigate("search")}) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Localized description",
                                tint = Color.White,
                            )
                        }
                    },

                        colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF242B33),
                        titleContentColor = Color.White,
                    ),
                    title = {
                        Text("Historical Data")
                    }
                )
            },
        ) { innerPadding ->
            val address = viewModel.jaga_ka_nam
            val jaga = viewModel.jaga_ka_offline_naam
            val context = LocalContext.current
            val maindata=viewModel.weatherData.value?.data
            Column(
                modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
            ) {
                Column(
                    modifier
                        .padding(20.dp)
                        .align(Alignment.CenterHorizontally)) {
                    Text(
                        text = if(address!=null){
                            setOfNotNull(
                                address?.featureName,
                                address?.locality,
                                address?.countryName
                            ).joinToString(separator = " ")
                        } else {
                               jaga
                        },
                        color = Color.White,
                        modifier = modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text =viewModel.date ,
                        color = Color.White,
                        modifier = modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                    )
                    val imageName = maindata?.daily?.weather_code?.let { getImageName(it.get(0)) }
                    val imageResourceId = context.resources.getIdentifier(
                        imageName,
                        "drawable",
                        context.packageName
                    )

                    Box(  modifier = modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 25.dp)
                        .size(200.dp))
                    {
                        Image(modifier = modifier.fillMaxSize(),
                            painter = painterResource(id = imageResourceId),
                            contentDescription = "Background"
                        )
                    }

                    Text(
                        text = (maindata?.daily?.weather_code?.let { getWeatherStatus(it.get(0)) }).toString(),
                        color = Color.White,
                        modifier = modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(10.dp),
                        style = MaterialTheme.typography.labelMedium,
                        fontSize = 13.sp
                    )
                    Column(
                        modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(10.dp))
                    {

                        Row(
                            modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(vertical = 15.dp, horizontal = 10.dp)){
                            Column(modifier = Modifier
                                .padding(5.dp)
                                .requiredWidth(150.dp)){
                                Text(text = "Min Temp: ",
                                    color = Color.White,
                                    modifier = modifier.align(Alignment.CenterHorizontally),
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold,)
                                if (maindata != null) {
                                    val averageTemperature = maindata.daily.temperature_2m_min.averageOrNull()
                                    val formattedAverageTemperature = averageTemperature?.let { "%.2f".format(it).toFloat() } ?: 0.0f

                                    Text(text = "${formattedAverageTemperature}°C",                                        color = Color.White,
                                        modifier = modifier.align(Alignment.CenterHorizontally),
                                        style = MaterialTheme.typography.headlineSmall,
                                        fontWeight = FontWeight.SemiBold,)
                                }
                            }
                            Column(modifier = Modifier
                                .padding(5.dp)
                                .requiredWidth(150.dp)){
                                Text(text = "Max Temp: ",
                                    color = Color.White,
                                    modifier = modifier.align(Alignment.CenterHorizontally),
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold,)
                                if (maindata != null) {
                                    val averageMaxTemperature = maindata.daily.temperature_2m_max.averageOrNull()
                                    val formattedAverageMaxTemperature = averageMaxTemperature?.let { "%.2f".format(it).toFloat() } ?: 0.0f

                                    Text(text = "${formattedAverageMaxTemperature}°C",
                                        color = Color.White,
                                        modifier = modifier.align(Alignment.CenterHorizontally),
                                        style = MaterialTheme.typography.headlineSmall,
                                        fontWeight = FontWeight.SemiBold,)
                                }
                            }
                        }
                        val averageShortwaveRadiationSum = maindata?.daily?.shortwave_radiation_sum?.averageOrNull() ?: 0.0
                        val uvIndex = (averageShortwaveRadiationSum / 9).toInt()
                        val averageRainSum = maindata?.daily?.rain_sum?.FaverageOrNull()?.let { "%.2f".format(it) } ?: "0.0"
                        val averagePrecipitationSum = maindata?.daily?.precipitation_sum?.FaverageOrNull()?.let { "%.2f".format(it) } ?: "0.0"
                        val averageWindSpeedMax = maindata?.daily?.wind_speed_10m_max?.averageOrNull()?.let { "%.2f".format(it) } ?: "0.0"
                        val averageWindGustsMin = maindata?.daily?.wind_gusts_10m_max?.averageOrNull()?.let { "%.2f".format(it) } ?: "0.0"

                        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                            cardinfo(head = "UV Index", Data = uvIndex.toString())
                            cardinfo(head = "Rain Sum", Data = averageRainSum)
                        }

                        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                            cardinfo(head = "Precipitation Sum", Data = "$averagePrecipitationSum mm")
                            cardinfo(head = "Wind Direction", Data = "${maindata?.daily?.wind_direction_10m_dominant?.get(0)}°")
                        }

                        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                            cardinfo(head = "Wind Speed Max", Data = averageWindSpeedMax)
                            cardinfo(head = "Wind Gusts Min", Data = averageWindGustsMin)
                        }

                    }
                }

            }

        }
    }
}
fun List<Double>.averageOrNull(): Double? {
    return if (isEmpty()) null else average()
}
fun List<Float>.FaverageOrNull(): Double? {
    return if (isEmpty()) null else average()
}

@Composable
fun cardinfo(head:String,Data:String){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor=Color(0xFF2E3741)
        ),
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .requiredWidth(150.dp)
    ) {
        Text(
            text = head,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 5.dp),
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
        )
        Text(
            text = Data,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 5.dp),
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
        )
    }
}
@Preview
@Composable
fun presecond() {
    val datamodel: WeatherViewModel = viewModel()

    MainScreen().ShowScreen(Modifier, datamodel, rememberNavController())
}
