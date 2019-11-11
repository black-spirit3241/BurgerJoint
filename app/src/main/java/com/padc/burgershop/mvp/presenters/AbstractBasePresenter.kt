package com.padc.burgershop.mvp.presenters

import androidx.lifecycle.ViewModel
import com.padc.burgershop.mvp.views.BaseView

abstract class AbstractBasePresenter<T: BaseView>  : BasePresenter<T> , ViewModel(){
    protected lateinit var mView:T

    override fun initPresenter(v: T) {
        mView=v
    }
}