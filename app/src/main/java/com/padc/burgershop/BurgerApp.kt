package com.padc.burgershop

import android.app.Application
import com.padc.burgershop.data.model.impl.BurgerModelImpl
import com.padc.burgershop.dummy.getDummyBurgers
import com.padc.burgershop.persistence.BurgerDatabase

class BurgerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        BurgerModelImpl.init(applicationContext)
        deleteAllBurgers()
        prePopulateBurgers()
    }

    private fun deleteAllBurgers(){
        BurgerDatabase.getInstance(applicationContext)
            .BurgerDao()
            .deleteAllBurgers()
    }

    private fun prePopulateBurgers(){
        BurgerDatabase.getInstance(applicationContext).BurgerDao()
            .insertBurgers(getDummyBurgers())
    }
}