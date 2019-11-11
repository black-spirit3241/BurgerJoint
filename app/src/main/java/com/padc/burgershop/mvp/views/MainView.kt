package com.padc.burgershop.mvp.views

import com.padc.burgershop.data.vos.BurgerVO

interface MainView : BaseView {

    fun displayBurgerList(burgerList:List<BurgerVO>)
    fun navigateToDetailScreen(burgerId : Int)
    fun navigateToCartScreen()
    fun addBurgerToCart(burger : BurgerVO)
    fun dispalyCountInCart(burgerCount : Int)
}