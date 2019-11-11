package com.padc.burgershop.mvp.presenters

import com.padc.burgershop.delegates.BurgerViewHolderActionDelegate
import com.padc.burgershop.mvp.views.MainView

interface MainPresenter : BasePresenter<MainView>,BurgerViewHolderActionDelegate{
    fun onTapCart()
}