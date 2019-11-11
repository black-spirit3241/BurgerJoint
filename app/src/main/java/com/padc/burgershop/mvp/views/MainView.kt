package com.padc.burgershop.mvp.views

import android.widget.ImageView
import android.widget.TextView
import com.padc.burgershop.data.vos.BurgerVO

interface MainView : BaseView {

    fun displayBurgerList(burgerList:List<BurgerVO>)
    fun navigateToDetailScreen(burgerId : Int,burgerImageView: ImageView,burgerName: TextView)
    fun navigateToCartScreen()
    fun addBurgerToCart(burger : BurgerVO,burgerImageView: ImageView)
    fun displayCountInCart(burgerCount : Int)
}