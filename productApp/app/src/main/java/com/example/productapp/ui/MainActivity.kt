package com.example.productapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.productapp.data.model.ProductModel
import com.example.productapp.viewModel.MainViewModel
import com.example.productapp.R
import com.example.productapp.databinding.ActivityMainBinding
import com.example.productapp.ui.ListProductActivity
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding;
    private lateinit var mainVM: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonCadastrar.setOnClickListener(this)

        binding.trocarpagina.setOnClickListener(this)

        mainVM = ViewModelProvider(this).get(MainViewModel::class.java)
//        setObserverTextTop()
        binding.textTop.text = "Registrar Produtos"
        setObserverTextTopRegister()
    }

    override fun onClick(view: View) {
        if(view.id == R.id.button_cadastrar){
            val idProduct = binding.editTextProductCod.text.toString()
            val nameProduct = binding.editTextProductName.text.toString()

            try {
                val p = ProductModel().apply {
                    this.name = binding.editTextProductName.text.toString()
                    this.prodCode = binding.editTextProductCod.text.toString()
                    this.amount = 1
                    this.price = 25f
                }
                if (p.name == ""){
                    Toast.makeText(this, R.string.empty_name_msg, Toast.LENGTH_SHORT).show()
                } else if (p.prodCode == "") {
                    Toast.makeText(this, R.string.empty_prod_code_msg, Toast.LENGTH_SHORT).show()
                }

                mainVM.registerProduct(p)
            }catch (e: NumberFormatException){
                Toast.makeText(this, R.string.empty_number_msg, Toast.LENGTH_SHORT).show()
            }

//            mainVM.registerProduct(idProduct, nameProduct)
//            Toast.makeText(applicationContext, "teste", Toast.LENGTH_SHORT).show()
        } else if (view.id == R.id.trocarpagina) {
            val intent = Intent(this, ListProductActivity::class.java)
            startActivity(intent)
        }


    }

//    private fun setObserverTextTop() {
//        mainVM.titleTop().observe(this, Observer {
//            binding.textTop.text = it
//        })
//    }

    private fun setObserverTextTopRegister(){
        mainVM.isRegistered().observe(this, Observer {
            if (it == R.string.success_resgister){
                clearEdits()
            }
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun clearEdits(){
        binding.editTextProductCod.getText().clear()
        binding.editTextProductName.getText().clear()
    }

}