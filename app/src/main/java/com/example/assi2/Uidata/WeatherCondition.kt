package com.example.assi2.Uidata

enum class WeatherCondition(val code: Int, val imageName: String) {
    CLOUD_DEVELOPMENT_NOT_OBSERVED_Day(0, "weather_2_sunny"),

    CLOUDS_DISSOLVING_Day(1, "weather_2_jadatar_sunny_day",),

    UNCHANGED_SKY(2, "weather_2_partly_cloudy_day"),

    CLOUDS_FORMING(3, "weather_2_partly_cloudy_day"),

    REDUCED_VISIBILITY(4, "fogg"),

    HAZE(5, "weather_2_haze"),

    WIDESPREAD_DUST(6, "weather_2_overcast"),
    DUST_OR_SAND_RAISED(7, "weather_2_overcast"),
    DEVELOPED_DUST_WHIRLS(8, "weather_2_overcast"),
    DUSTSTORM_OR_SANDSTORM(9, "weather_2_overcast"),
    MIST(10, "weather_2_mist"),
    PATCHES(11, "weather_2_mist"),
    CONTINUOUS(12, "weather_2_cloudy"),
    LIGHTNING_VISIBLE(13, "thunder"),
    PRECIPITATION_NOT_REACHING_GROUND(14, "precipitation"),
    DISTANT_PRECIPITATION(15, "precipitation"),
    NEAR_PRECIPITATION(16, "precipitation"),
    THUNDERSTORM_NO_PRECIPITATION(17, "weather_2_cloud_rain_thunder"),
    SQUALLS(18, "weather_2_iso_rain_swrs_day"),
    FUNNEL_CLOUD(19, "weather_2_overcast"),
    DRIZZLE_OR_SNOW_GRAINS(20, "weather_2_snow_rain"),
    RAIN(21, "weather_2_freezing_rain"),
    SNOW(22, "weather_2_heavy_snow_swrs_night"),
    RAIN_AND_SNOW(23, "weather_2_snow_rain"),
    FREEZING_DRIZZLE_OR_FREEZING_RAIN(24, "weather_2_iso_rain_swrs_night"),
    RAIN_SHOWERS(25, "weather_2_heavy_rain"),
    SNOW_SHOWERS(26, "weather_2_heavy_snow"),
    HAIL_SHOWERS(27, "weather_2_heavy_rain"),
    FOG_OR_ICE_FOG(28, "weather_2_heavy_snow"),
    THUNDERSTORM(29, "weather_2_cloud_rain_thunder"),
    DUSTSTORM_OR_SANDSTORM_DECREASED(30, "weather_2_overcast"),
    DUSTSTORM_OR_SANDSTORM_NO_CHANGE(31, "weather_2_overcast"),
    DUSTSTORM_OR_SANDSTORM_INCREASED(32, "weather_2_overcast"),
    SEVERE_DUSTSTORM_OR_SANDSTORM_DECREASED(33, "weather_2_overcast"),
    SEVERE_DUSTSTORM_OR_SANDSTORM_NO_CHANGE(34, "weather_2_overcast"),
    SEVERE_DUSTSTORM_OR_SANDSTORM_INCREASED(35, "weather_2_overcast"),
    SLIGHT_BLOWING_SNOW(36, "weather_2_overcast"),
    HEAVY_DRIFTING_SNOW(37, "wind_snow"),
    MODERATE_BLOWING_SNOW(38, "wind_snow"),
    HEAVY_DRIFTING_SNOW_2(39, "wind_snow"),
    FOG_OR_ICE_FOG_AT_OBSERVATION(40, "fogg"),
    FOG_OR_ICE_FOG_IN_PATCHES(41, "fogg"),
    FOG_OR_ICE_FOG_THINNER_SKY_VISIBLE(42, "fogg"),
    FOG_OR_ICE_FOG_INVISIBLE_SKY_VISIBLE(43, "fogg"),
    FOG_OR_ICE_FOG_INVISIBLE_SKY_INVISIBLE(44, "fogg"),
    FOG_OR_ICE_FOG_THICKER_SKY_VISIBLE(45, "fogg"),
    FOG_OR_ICE_FOG_INVISIBLE_SKY_INVISIBLE_2(46, "fogg"),
    FOG_DEPOSITING_RIME_SKY_VISIBLE(47, "fogg"),
    FOG_DEPOSITING_RIME_SKY_INVISIBLE(48, "fogg"),
    FOG_DEPOSITING_RIME(49, "fogg"),
    Drizzle1(50,"weather_2_occ_light_rain"),
    Drizzle2(51,"weather_2_occ_light_rain"),
    Drizzle3(52,"weather_2_occ_light_rain"),
    Drizzle4(53,"weather_2_occ_light_rain"),
    Drizzle5(54,"weather_2_occ_light_rain"),
    Drizzle9(55,"weather_2_occ_light_rain"),
    Drizzle10(56,"weather_2_occ_light_rain"),
    Drizzle11(57,"weather_2_occ_light_rain"),
    Drizzle12(58,"weather_2_occ_light_rain"),
    Drizzle13(59,"weather_2_occ_light_rain"),
    Drizzle14(60,"weather_2_occ_light_rain"),
    Drizzle15(61,"weather_2_occ_light_rain"),
    Drizzle16(62,"weather_2_occ_light_rain"),
    Drizzle17(63,"weather_2_occ_light_rain"),
    Drizzle18(64,"weather_2_occ_light_rain"),
    Drizzle19(65,"weather_2_occ_light_rain"),
    Drizzle20(66,"weather_2_occ_light_rain"),
    Drizzle21(67,"weather_2_occ_light_rain"),
    Drizzle22(68,"weather_2_occ_light_rain"),
    Drizzle23(69,"weather_2_occ_light_rain"),
    Snow1(70,"weather_2_blizzard"),
    Snow2(71,"weather_2_blizzard"),
    Snow3(72,"weather_2_blizzard"),
    Snow4(73,"weather_2_blizzard"),
    Snow5(74,"weather_2_blizzard"),
    Snow6(75,"weather_2_blizzard"),
    Snow7(76,"weather_2_blizzard"),
    Snow8(78,"weather_2_blizzard"),
    Snow9(79,"weather_2_blizzard"),
    RAIN_SHOWER_SLIGHT(80, "weather_2_heavy_rain"),
    RAIN_SHOWER_MODERATE_HEAVY(81, "weather_2_heavy_rain"),
    RAIN_SHOWER_VIOLENT(82, "weather_2_heavy_rain"),
    RAIN_AND_SNOW_SHOWER_SLIGHT(83, "weather_2_blizzard"),
    RAIN_AND_SNOW_SHOWER_MODERATE_HEAVY(84, "weather_2_blizzard"),
    SNOW_SHOWER_SLIGHT(85, "weather_2_blizzard"),
    SNOW_SHOWER_MODERATE_HEAVY(86, "weather_2_blizzard"),
    SNOW_PELLETS_OR_SMALL_HAIL_SHOWER_SLIGHT(87, "hailstorm"),
    SNOW_PELLETS_OR_SMALL_HAIL_SHOWER_MODERATE_HEAVY(88, "hailstorm"),
    HAIL_SHOWER_SLIGHT(89, "hailstorm"),
    HAIL_SHOWER_MODERATE_HEAVY(90, "hailstorm"),
    SLIGHT_RAIN_THUNDERSTORM_PREVIOUS_HOUR(91, "weather_2_cloud_rain_thunder"),
    MODERATE_HEAVY_RAIN(92, "weather_2_heavy_rain"),
    SLIGHT_SNOW_OR_RAIN_AND_SNOW_MIXED_OR_HAIL(93, "hailstorm"),
    MODERATE_HEAVY_SNOW_OR_RAIN_AND_SNOW_MIXED_OR_HAIL(94, "hailstorm"),
    SLIGHT_OR_MODERATE_THUNDERSTORM_WITHOUT_HAIL(95, "thunder"),
    SLIGHT_OR_MODERATE_THUNDERSTORM_WITH_HAIL(96, "weather_2_cloud_sleet_snow_thunder"),
    HEAVY_THUNDERSTORM_WITHOUT_HAIL(97, "thunder"),
    THUNDERSTORM_WITH_DUSTSTORM_OR_SANDSTORM(98, "weather_2_cloud_sleet_snow_thunder"),
    HEAVY_THUNDERSTORM_WITH_HAIL(99, "weather_2_cloud_sleet_snow_thunder")
}

// Function to get the image name based on the weather code
fun getImageName(weatherCode: Int): String {
    val weatherCondition = WeatherCondition.entries.find { it.code == weatherCode }
    return weatherCondition?.imageName ?: "default_image" // Default image if weather code not found
}


fun getWeatherStatus(weatherCode: Int): String {
    return when (weatherCode) {
        in 0..19 -> "No precipitation at the station at the time of observation"
        20 -> "Drizzle (not freezing) or snow grains not falling as shower(s)"
        21 -> "Rain (not freezing)"
        22 -> "Snow"
        23 -> "Rain and snow or ice pellets"
        24 -> "Freezing drizzle or freezing rain"
        in 25..26 -> "Shower(s) of rain, snow, or rain and snow mixed"
        27 -> "Shower(s) of hail, or of rain and hail"
        28 -> "Fog or ice fog"
        29 -> "Thunderstorm (with or without precipitation)"
        in 30..39 -> "Duststorm, sandstorm, drifting or blowing snow"
        in 40..49 -> "Fog or ice fog at the time of observation"
        in 50..59 -> "Drizzle or rain"
        in 60..69 -> "Rain, freezing, moderate or heavy (dence)"
        in 70..79 -> "Solid precipitation not in showers"
        in 80..99 -> "Showery precipitation, or precipitation with current or recent thunderstorm"
        else -> "Unknown weather condition"
    }
}
