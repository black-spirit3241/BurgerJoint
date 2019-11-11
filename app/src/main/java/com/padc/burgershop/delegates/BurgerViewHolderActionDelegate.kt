package com.padc.burgershop.delegates

import android.widget.ImageView
import android.widget.TextView
import com.padc.burgershop.data.vos.BurgerVO

interface BurgerViewHolderActionDelegate {

    fun onTapBurger(burger : BurgerVO,burgerImageView: ImageView,burgerName : TextView)
    fun onTapAddToCart(burger: BurgerVO,burgerImageView:ImageView)
}