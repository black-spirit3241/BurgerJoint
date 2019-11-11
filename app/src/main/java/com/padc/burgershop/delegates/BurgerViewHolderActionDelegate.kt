package com.padc.burgershop.delegates

import com.padc.burgershop.data.vos.BurgerVO

interface BurgerViewHolderActionDelegate {

    fun onTapBurger(burger : BurgerVO)
    fun onTapAddToCart(burger: BurgerVO)
}