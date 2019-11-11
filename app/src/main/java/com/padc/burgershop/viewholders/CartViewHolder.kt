package com.padc.burgershop.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.padc.burgershop.data.vos.BurgerVO
import com.padc.burgershop.delegates.CartViewHolderActionDelegate
import kotlinx.android.synthetic.main.view_item_cart.view.*

class CartViewHolder(itemView: View, private val mDelegate : CartViewHolderActionDelegate) :
BaseViewHolder<BurgerVO>(itemView){

    init {
        itemView.btnRemoveFromCart.setOnClickListener{
            mData?.let {
                mDelegate.onTapRemoveFromCard(it)
            }
        }
    }


    override fun bindData(data: BurgerVO) {
        mData=data

        itemView.tvBurgerName.text=data.burgerName
        itemView.tvBurgerDescription.text=data.burgerDescription

        Glide.with(itemView.ivBurger)
            .load(data.burgerImageUrl)
            .into(itemView.ivBurger)
    }
}