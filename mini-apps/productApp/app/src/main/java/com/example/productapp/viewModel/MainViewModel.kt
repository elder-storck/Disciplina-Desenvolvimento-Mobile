package com.example.productapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.productapp.R
import com.example.productapp.repository.ProductRepository

class MainViewModel: ViewModel() {
    private var textTop = MutableLiveData<String>()
    private var register = MutableLiveData<Boolean>()
    private val productRepository = ProductRepository()

    init {
        textTop.value = "Registrar Produtos"
    }
    fun titleTop(): LiveData<String> {
        return textTop
    }


    fun registerProduct(idProd: String, nameProd: String){
        register.value = productRepository.register(idProd, nameProd)
    }

    fun isRegistered(): LiveData<Boolean> {
        return register
    }
}