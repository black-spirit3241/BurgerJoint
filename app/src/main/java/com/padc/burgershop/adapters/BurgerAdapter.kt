package com.padc.burgershop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padc.burgershop.R
import com.padc.burgershop.data.vos.BurgerVO
import com.padc.burgershop.delegates.BurgerViewHolderActionDelegate
import com.padc.burgershop.viewholders.BurgerViewHolder

class BurgerAdapter(private val mDelegate:BurgerViewHolderActionDelegate) : BaseRecyclerAdapter<BurgerViewHolder,BurgerVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BurgerViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.view_item_burger,parent,false)
        return BurgerViewHolder(view,mDelegate)
    }

}