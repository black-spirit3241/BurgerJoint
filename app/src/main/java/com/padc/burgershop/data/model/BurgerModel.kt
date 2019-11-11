package com.padc.burgershop.data.model

import androidx.lifecycle.LiveData
import com.padc.burgershop.data.vos.BurgerVO

interface BurgerModel{
    fun getAllBurgers():LiveData<List<BurgerVO>>
    fun findBurgerById(burgerId : Int) : LiveData<BurgerVO>
    fun getBurgersInCart():LiveData<List<BurgerVO>>
    fun removeItemFromCart(burger:BurgerVO)
    fun addItemToCart(burger: BurgerVO)
}