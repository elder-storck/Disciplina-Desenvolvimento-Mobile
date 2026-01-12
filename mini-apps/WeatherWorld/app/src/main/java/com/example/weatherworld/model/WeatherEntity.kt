package com.example.weatherworld.model

import com.google.gson.annotations.SerializedName

//Cidade
//estado
//temp
//clima
//temp max e min
//sensação termica
//velocidade vento
//humidade do ar

data class WeatherEntity(

    @SerializedName("name")
    val cityName: String?,                       // Nome da cidade

    @SerializedName("main")
    val main: MainInfo?,                         // Temperatura, umidade...

    @SerializedName("weather")
    val weather: List<WeatherDescription>?,      // Descrição do clima

    @SerializedName("wind")
    val wind: WindInfo?,                         // Velocidade/ direção do vento

//    @SerializedName("dt")
//    val timestamp: Long                          // Horário medição (epoch)
)

data class MainInfo(
    @SerializedName("temp")
    val temp: Double,                             // temperatura atual

    @SerializedName("temp_min")
    val tempMin: Double,                          // mínima

    @SerializedName("temp_max")
    val tempMax: Double,                          // máxima

    @SerializedName("feels_like")
    val feelsLike: Double,                        // sensação térmica

    @SerializedName("humidity")
    val humidity: Int                             // umidade do ar
)

data class WeatherDescription(
    @SerializedName("main")
    val main: String,                             // Clear, Clouds, Rain...

    @SerializedName("id")
    val id: Int,

//    @SerializedName("description")
//    val description: String,                      // descrição completa
//
    @SerializedName("icon")
    val icon: String                              // código do ícone da OWM
)

data class WindInfo(
    @SerializedName("speed")
    val speed: Double                            // velocidade do vento
)
