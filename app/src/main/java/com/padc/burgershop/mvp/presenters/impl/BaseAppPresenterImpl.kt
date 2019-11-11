package com.padc.burgershop.mvp.presenters.impl

import com.padc.burgershop.mvp.presenters.AbstractBasePresenter
import com.padc.burgershop.mvp.views.BaseView

abstract class BaseAppPresenterImpl<T: BaseView> :
AbstractBasePresenter<T>(){
}