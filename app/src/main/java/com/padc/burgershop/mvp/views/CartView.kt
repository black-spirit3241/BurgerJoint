package com.padc.burgershop.mvp.views

import com.padc.burgershop.data.vos.BurgerVO

interface CartView : BaseView {
    fun displayItemsInCart(burgers : List<BurgerVO>)
    fun displayThankYouMessage()
    fun hideThankYouMessage()
}