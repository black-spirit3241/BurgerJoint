package com.padc.burgershop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padc.burgershop.R
import com.padc.burgershop.data.vos.BurgerVO
import com.padc.burgershop.delegates.CartViewHolderActionDelegate
import com.padc.burgershop.viewholders.CartViewHolder

class CartAdapter(private val mDelegate : CartViewHolderActionDelegate) :
BaseRecyclerAdapter<CartViewHolder,BurgerVO>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_item_cart,parent,false)
        return CartViewHolder(view,mDelegate)
    }

}