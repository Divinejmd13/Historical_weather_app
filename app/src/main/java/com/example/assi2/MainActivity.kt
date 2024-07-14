package com.example.assi2

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assi2.Api.Resource
import com.example.assi2.Api.WeatherViewModel
import com.example.assi2.Database.HistoricalWeather
import com.example.assi2.Database.HistoricalWeatherDao
import com.example.assi2.Database.WeatherDatabase
import com.example.assi2.Database.WeatherRepository
import com.example.assi2.Uidata.Download
import com.example.assi2.Uidata.HomeScreen
import com.example.assi2.Uidata.MainScreen
import com.example.assi2.ui.theme.Assi2Theme
import com.example.assi2.ui.theme.Setcolorbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Suppress("DEPRECATION")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Setcolorbar().SetStatusBarColor(color = Color(0xFF02C650))
            val navController = rememberNavController()
            Assi2Theme {
                // A surface container using the 'background' color from the theme


                val database = WeatherDatabase.getDatabase(applicationContext)
                val dao = database.weatherDao()
                val repository = WeatherRepository(dao)
                val datamodel = WeatherViewModel(repository)

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController, startDestination = "home"){

                      composable("home") { HomeScreen(modifier = Modifier,applicationContext,datamodel,navController,isNetworkAvailable(applicationContext),false) }
                        composable("search") { HomeScreen(modifier = Modifier,applicationContext,datamodel,navController,isNetworkAvailable(applicationContext),true) }

                    composable("Second") {
                        MainScreen().ShowScreen(modifier = Modifier, viewModel =datamodel,navController )
                    }
                    composable("Download") {
                        Download().DownloadScreen(modifier = Modifier, viewModel = datamodel, navController = navController)
                    }

            }
            }
        }
    }
}
fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
    }


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val context = LocalContext.current // Add this line to get the Context
    Assi2Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val datamodel: WeatherViewModel= viewModel()
            val database = WeatherDatabase.getDatabase(applicationContext)
            val dao = database.weatherDao()
            HomeScreen(Modifier, context,datamodel, rememberNavController(),isNetworkAvailable(context),false)
        }
    }
}
}