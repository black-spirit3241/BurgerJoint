package com.padc.burgershop.mvp.views

import com.padc.burgershop.data.vos.BurgerVO

interface BurgerDetailView : BaseView {
    fun displayBurgerDetails(burger :BurgerVO)
}