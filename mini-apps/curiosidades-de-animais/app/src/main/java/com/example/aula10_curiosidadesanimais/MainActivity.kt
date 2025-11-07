package com.example.aula10_curiosidadesanimais

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aula10_curiosidadesanimais.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if(view.id == R.id.button_save){
            //pegar o nome
            val name = binding.editTextName.text.toString()

            val preferences = MyPreferences(this)
            preferences.setString("name", name)

            startActivity(Intent(this, tela2::class.java))
        }
    }
}