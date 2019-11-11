package com.padc.burgershop.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padc.burgershop.mvp.views.BaseView

 interface BasePresenter<view : BaseView> {

      fun onUIReady(owner : LifecycleOwner)
      fun initPresenter(v : view)
}