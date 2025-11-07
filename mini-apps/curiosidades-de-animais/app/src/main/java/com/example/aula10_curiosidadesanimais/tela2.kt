package com.example.aula10_curiosidadesanimais

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.aula10_curiosidadesanimais.databinding.ActivityTela2Binding

class tela2 : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityTela2Binding
    private val viewModel: AnimalViewModel by viewModels()
    private var selectedAnimal : Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar o ViewBinding
        binding = ActivityTela2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGenerateAnother.setOnClickListener(this)
        binding.dogImage.setOnClickListener(this)
        binding.catImage.setOnClickListener(this)

        // Recuperar o nome
        val preferences = MyPreferences(this)
        val name = preferences.getString("name")

        if (name.isNotEmpty()) {
            binding.textWelcome.text = getString(R.string.welcome_text, name)
        } else {
            binding.textWelcome.text = getString(R.string.default_welcome)
        }

        // Inicia com o cachorro acionado
        binding.factTextView.text = viewModel.getDogFacts()
        binding.dogImage.setColorFilter(ContextCompat.getColor(this, R.color.accent_orange))
        selectedAnimal = 0


    }

    override fun onClick(view: View) {
        // Botão gerar outra frase
        if(view.id == R.id.button_generate_another){
            if(selectedAnimal == 0){
                binding.factTextView.text = viewModel.getDogFacts()
            }else{
                binding.factTextView.text = viewModel.getCatFacts()
            }
        }

        // Botão seleciona gato
        if (view.id == R.id.cat_image){
            selectedAnimal = 1 //gato
            binding.dogImage.setColorFilter(ContextCompat.getColor(this, R.color.white))
            binding.catImage.setColorFilter(ContextCompat.getColor(this, R.color.accent_orange))

        }

        // Botão seleciona Cachorro
        if (view.id == R.id.dog_image){
            selectedAnimal = 0 // cachorro
            binding.dogImage.setColorFilter(ContextCompat.getColor(this, R.color.accent_orange))
            binding.catImage.setColorFilter(ContextCompat.getColor(this, R.color.white))
        }
    }
}