package com.padc.burgershop.mvp.presenters.impl

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padc.burgershop.data.model.impl.BurgerModelImpl
import com.padc.burgershop.data.vos.BurgerVO
import com.padc.burgershop.mvp.presenters.MainPresenter
import com.padc.burgershop.mvp.views.MainView


class MainPresenterImpl : BaseAppPresenterImpl<MainView>(),MainPresenter{

    private val mBurgerModel = BurgerModelImpl

    override fun onTapCart() {
        mView.navigateToCartScreen()
    }

    override fun onTapBurger(burger: BurgerVO) {
        mView.navigateToDetailScreen(burger.burgerId)
    }

    override fun onTapAddToCart(burger: BurgerVO) {
        mBurgerModel.addItemToCart(burger)
        mView.addBurgerToCart(burger)
    }


    override fun onUIReady(owner: LifecycleOwner) {
        mBurgerModel.getAllBurgers()
            .observe(owner, Observer {
                mView.displayBurgerList(it)
            })

        mBurgerModel.getBurgersInCart()
            .observe(owner, Observer {
                mView.dispalyCountInCart(it.count())
            })
    }
}