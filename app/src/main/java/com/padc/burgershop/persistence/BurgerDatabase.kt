package com.padc.burgershop.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.padc.burgershop.data.vos.BurgerVO
import com.padc.burgershop.persistence.daos.BurgerDao

const val DB_NAME="burgers.db"

@Database(entities = [BurgerVO::class],version = 1,exportSchema = false)
abstract class BurgerDatabase : RoomDatabase(){


    companion object{
        private  var instance : BurgerDatabase? = null
        fun getInstance(context: Context) : BurgerDatabase{
            if(instance == null){
                instance= Room.databaseBuilder(context,BurgerDatabase::class.java, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance as BurgerDatabase
        }
    }

    abstract fun BurgerDao() : BurgerDao
}