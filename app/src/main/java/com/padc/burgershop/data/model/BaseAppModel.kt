package com.padc.burgershop.data.model

import android.content.Context
import com.padc.burgershop.persistence.BurgerDatabase

abstract class BaseAppModel : BaseModel() {

    protected  lateinit var mDatabase : BurgerDatabase
    override fun init(context: Context) {
        initDatabase(context)
    }

    private fun initDatabase(context: Context){
        mDatabase=BurgerDatabase.getInstance(context)
    }
}