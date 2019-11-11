package com.padc.burgershop.mvp.presenters.impl

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padc.burgershop.data.model.impl.BurgerModelImpl
import com.padc.burgershop.data.vos.BurgerVO
import com.padc.burgershop.mvp.presenters.CartPresenter
import com.padc.burgershop.mvp.views.CartView

class CartPresenterImpl : CartPresenter, BaseAppPresenterImpl<CartView>() {

    private val mBurgerModel = BurgerModelImpl

    override fun onTapCheckout() {
        mView.displayThankYouMessage()
    }

    override fun onTapCancelThankYouMessage() {
        mView.hideThankYouMessage()
    }

    override fun onUIReady(owner: LifecycleOwner) {
        mBurgerModel
            .getBurgersInCart()
            .observe(owner, Observer {
                mView.displayItemsInCart(it)
            })
    }

    override fun onTapRemoveFromCard(burger: BurgerVO) {
        mBurgerModel
            .removeItemFromCart(burger)
    }
}