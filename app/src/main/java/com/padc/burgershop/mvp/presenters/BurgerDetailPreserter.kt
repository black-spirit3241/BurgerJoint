package com.padc.burgershop.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padc.burgershop.mvp.views.BurgerDetailView

interface BurgerDetailPreserter : BasePresenter<BurgerDetailView>{
    fun onBurgerDetailsUiReady(lifecycleOwner: LifecycleOwner, burgerDetailsId: Int)
}