package com.example.weatherworld

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.weatherworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonPrevise.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.buttonPrevise){
            //pega o name
            val city = binding.cityName.text.toString()

            val preferences = MyPreferences(this)
            preferences.setString("city", city)

            startActivity(Intent(this, DescriptionWeather::class.java))

        }



    }
}