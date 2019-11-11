package com.padc.burgershop.mvp.presenters.impl

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padc.burgershop.data.model.impl.BurgerModelImpl
import com.padc.burgershop.mvp.presenters.BurgerDetailPreserter
import com.padc.burgershop.mvp.views.BurgerDetailView

class BurgerDetailPresenterImpl : BurgerDetailPreserter, BaseAppPresenterImpl<BurgerDetailView>(){

    private val mBurgerModel = BurgerModelImpl

    override fun onBurgerDetailsUiReady(lifecycleOwner: LifecycleOwner, burgerDetailsId: Int) {
        mBurgerModel.findBurgerById(burgerDetailsId)
            .observe(lifecycleOwner, Observer {
                mView.displayBurgerDetails(it)
            })
    }

    override fun onUIReady(owner: LifecycleOwner) {

    }
}