package com.example.productapp.viewModel

import android.app.Application
import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.productapp.data.model.ProductModel
import com.example.productapp.data.room.AppDatabase
import com.example.productapp.R
import com.example.productapp.repository.ProductRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {
//    private var textTop = MutableLiveData<String>()
    private val register = MutableLiveData<Int>()

//    init {
//        textTop.value = "Registrar Produtos"
//    }
//    fun titleTop(): LiveData<String> {
//        return textTop
//    }


    fun registerProduct(p : ProductModel){
        val db = AppDatabase.getDatabase(getApplication()).ProductDAO()
        var resp = 0L
        try {
            resp = db.insert(p)
            register.value = if(resp > 0) R.string.success_resgister else R.string.fail_register
        } catch (e: SQLiteConstraintException){
            register.value = R.string.error
        }

//        register.value = productRepository.register(idProd, nameProd)
    }

    fun isRegistered(): LiveData<Int> {
        return register
    }
}