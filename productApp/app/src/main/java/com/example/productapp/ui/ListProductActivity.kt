package com.example.productapp.ui

import ListProductAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productapp.data.model.ProductModel
import com.example.productapp.data.room.AppDatabase
import com.example.productapp.databinding.ActivityListProductBinding

class ListProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListProductBinding
    private lateinit var adapter: ListProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dao = AppDatabase.getDatabase(this).ProductDAO()
        val productList = dao.getAll()

        binding.recyclerListProducts.layoutManager = LinearLayoutManager(this)

        adapter = ListProductAdapter(productList)
        binding.recyclerListProducts.adapter = adapter

    }
}
