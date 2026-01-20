package com.example.productapp.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.productapp.data.dao.ProductDAO
import com.example.productapp.data.model.ProductModel

@Database(entities = [ProductModel::class], version = 1)
abstract class AppDatabase(): RoomDatabase() {

    abstract fun ProductDAO(): ProductDAO

    companion object {
        private lateinit var INSTANCE: AppDatabase
        fun getDatabase(context: Context): AppDatabase {

            if(!::INSTANCE.isInitialized) {

                synchronized(AppDatabase::class) {

                    INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, "mydatabase.db")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}