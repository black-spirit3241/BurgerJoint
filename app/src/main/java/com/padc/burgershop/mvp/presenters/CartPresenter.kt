package com.padc.burgershop.mvp.presenters

import androidx.cardview.widget.CardView
import com.padc.burgershop.delegates.CartViewHolderActionDelegate
import com.padc.burgershop.mvp.views.CartView

interface CartPresenter : BasePresenter<CartView>,CartViewHolderActionDelegate{
    fun onTapCheckout()
    fun onTapCancelThankYouMessage()
}