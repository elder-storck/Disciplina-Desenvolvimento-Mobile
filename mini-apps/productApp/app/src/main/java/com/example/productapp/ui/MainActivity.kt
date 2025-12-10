package com.example.productapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.productapp.viewModel.MainViewModel
import com.example.productapp.R
import com.example.productapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding;
    private lateinit var mainVM: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonCadastrar.setOnClickListener(this)

        mainVM = ViewModelProvider(this).get(MainViewModel::class.java)
        setObserverTextTop()
        setObserverTextTopRegister()
    }

    override fun onClick(view: View) {
        if(view.id == R.id.button_cadastrar){
            val idProduct = binding.editTextProductCod.text.toString()
            val nameProduct = binding.editTextProductName.text.toString()

            mainVM.registerProduct(idProduct, nameProduct)
//            Toast.makeText(applicationContext, "teste", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setObserverTextTop() {
        mainVM.titleTop().observe(this, Observer {
            binding.textTop.text = it
        })
    }

    private fun setObserverTextTopRegister(){
        mainVM.isRegistered().observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, R.string.success_resgister, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, R.string.fail_register, Toast.LENGTH_SHORT).show()
            }
        })
    }

}