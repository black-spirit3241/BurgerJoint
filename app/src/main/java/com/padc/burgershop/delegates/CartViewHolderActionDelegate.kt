package com.padc.burgershop.delegates

import com.padc.burgershop.data.vos.BurgerVO

interface CartViewHolderActionDelegate{
    fun onTapRemoveFromCard(burger:BurgerVO)
}