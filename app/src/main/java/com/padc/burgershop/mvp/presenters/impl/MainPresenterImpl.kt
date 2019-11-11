package com.padc.burgershop.mvp.presenters.impl

import android.widget.ImageView
import android.widget.TextView
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

    override fun onTapBurger(burger: BurgerVO,burgerImageView: ImageView,burgerName : TextView) {
        mView.navigateToDetailScreen(burger.burgerId,burgerImageView,burgerName)
    }

    override fun onTapAddToCart(burger: BurgerVO,burgerImageView: ImageView) {
        mBurgerModel.addItemToCart(burger)
        mView.addBurgerToCart(burger,burgerImageView)
    }


    override fun onUIReady(owner: LifecycleOwner) {
        mBurgerModel.getAllBurgers()
            .observe(owner, Observer {
                mView.displayBurgerList(it)
            })

        mBurgerModel.getBurgersInCart()
            .observe(owner, Observer {
                mView.displayCountInCart(it.count())
            })
    }
}