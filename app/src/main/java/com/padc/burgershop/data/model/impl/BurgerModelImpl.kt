package com.padc.burgershop.data.model.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.padc.burgershop.data.model.BaseAppModel
import com.padc.burgershop.data.model.BurgerModel
import com.padc.burgershop.data.vos.BurgerVO

object  BurgerModelImpl : BurgerModel,BaseAppModel() {

    var burgersInOrder : MutableList<BurgerVO> = arrayListOf()
    var burgersInOrderLiveData = MutableLiveData<List<BurgerVO>>()

    override fun getAllBurgers(): LiveData<List<BurgerVO>> {
        return Transformations.distinctUntilChanged(
            mDatabase.BurgerDao().getAllBurgers()
        )

    }

    override fun findBurgerById(burgerId: Int): LiveData<BurgerVO> {
        return Transformations.distinctUntilChanged(
            mDatabase.BurgerDao().findBurgerById(burgerId)
        )

    }

    override fun getBurgersInCart(): LiveData<List<BurgerVO>> {
        burgersInOrderLiveData.postValue(burgersInOrder)
        return burgersInOrderLiveData
    }

    override fun removeItemFromCart(burger: BurgerVO) {
        burgersInOrder.remove(burger)
        burgersInOrderLiveData.postValue(burgersInOrder)
    }

    override fun addItemToCart(burger: BurgerVO) {
        burgersInOrder.add(burger)
        burgersInOrderLiveData.postValue(burgersInOrder)
    }
}