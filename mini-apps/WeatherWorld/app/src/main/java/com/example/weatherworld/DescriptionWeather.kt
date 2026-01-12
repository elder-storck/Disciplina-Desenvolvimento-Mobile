package com.example.weatherworld

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherworld.databinding.ActivityDescriptionWeatherBinding
import com.example.weatherworld.model.WeatherEntity
import com.example.weatherworld.network.ClientRetrofit
import com.example.weatherworld.service.WeatherGetService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DescriptionWeather : AppCompatActivity() {

    private lateinit var binding: ActivityDescriptionWeatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar o ViewBinding
        binding = ActivityDescriptionWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()  // Volta para a Activity anterior
        }

        loadWeather()
    }

    private fun loadWeather() {

        // Criar o serviço Retrofit
        val service = ClientRetrofit.createService(WeatherGetService::class.java)

//        Pegar name city
        val preferences = MyPreferences(this)
        val search_city = preferences.getString("city")

        // Criar a chamada da API
        val listCall = service.getWeatherByCity(
            cityName = search_city,
            apiKey = "f26f34971dba46d37a6a4eb6519c46da"
        )

        // Executar com enqueue()
        listCall.enqueue(object : Callback<WeatherEntity> {

            override fun onResponse(
                call: Call<WeatherEntity>,
                response: Response<WeatherEntity>
            ) {
                val weather = response.body()

                if (weather != null) {
                    binding.city.text = getString(R.string.city_name_desc, weather.cityName)

                    binding.temp.text = getString(R.string.temperature_value_with_comma,weather.main?.temp.toString())
                    binding.tempDescription.text = weather.weather?.firstOrNull()?.main ?: "-"

                    binding.tempMax.text = getString(R.string.temp_max,weather.main?.tempMax.toString())
                    binding.tempMin.text = getString(R.string.temp_min,weather.main?.tempMin.toString())
                    binding.feelsLike.text = getString(R.string.feels_like,weather.main?.feelsLike.toString())

                    binding.winSpeed.text = getString(R.string.wind_speed,weather.wind?.speed.toString())
                    binding.humidity.text = getString(R.string.humidity,weather.main?.humidity.toString())


//                    Código do estado do clima
                    val conditionId = weather.weather?.firstOrNull()?.id

                    when (conditionId) {
                        800 -> {
                            binding.clear.alpha = 1f
                            binding.tempDescription.text = getString(R.string.clear_sky)
                        }
                        in 200..232 -> {
                            binding.thunderstorm.alpha = 1f
                            binding.tempDescription.text = getString(R.string.thunderstorm)
                        }
                        in 300..321 -> {
                            binding.rain.alpha = 1f
                            binding.tempDescription.text = getString(R.string.drizzle)
                        }
                        in 500..531 -> {
                            binding.rain.alpha = 1f
                            binding.tempDescription.text = getString(R.string.rain)
                        }
                        in 600..622 -> {
                            binding.snow.alpha = 1f
                            binding.tempDescription.text = getString(R.string.snow)
                        }
                        in 801..804 -> {
                            binding.clouds.alpha = 1f
                            binding.tempDescription.text = getString(R.string.clouds)
                        }
                        in 700..781 -> {
                            binding.atmosphere.alpha = 1f
                            binding.tempDescription.text = getString(R.string.fog)
                        }
                        else -> {
                            binding.atmosphere.alpha = 1f
                            binding.tempDescription.text = getString(R.string.atmosphere)
                        }
                    }




                }
            }

            override fun onFailure(call: Call<WeatherEntity>, t: Throwable) {
                Toast.makeText(getApplicationContext(), R.string.api_fail, Toast.LENGTH_LONG).show()
            }
        })
    }
}
