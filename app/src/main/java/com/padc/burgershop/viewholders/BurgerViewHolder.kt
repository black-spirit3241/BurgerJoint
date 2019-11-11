package com.padc.burgershop.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.padc.burgershop.data.vos.BurgerVO
import com.padc.burgershop.delegates.BurgerViewHolderActionDelegate
import kotlinx.android.synthetic.main.view_item_burger.view.*
import kotlinx.android.synthetic.main.view_item_cart.view.*
import kotlinx.android.synthetic.main.view_item_cart.view.ivCardBurger
import kotlinx.android.synthetic.main.view_item_cart.view.tvBurgerDescription
import kotlinx.android.synthetic.main.view_item_cart.view.tvBurgerName

class BurgerViewHolder(itemView: View,private val mDelegate: BurgerViewHolderActionDelegate) : BaseViewHolder<BurgerVO>(itemView){

    init {
        itemView.ivCardBurger.setOnClickListener{
            mData?.let {
                mDelegate.onTapBurger(it)
            }
        }

        itemView.btnAddToCart.setOnClickListener{
            mData?.let {
                mDelegate.onTapAddToCart(it)
            }
        }
    }


    override fun bindData(data: BurgerVO) {
        mData=data

        itemView.tvBurgerName.text=data.burgerName
        itemView.tvBurgerDescription.text=data.burgerDescription

        Glide.with(itemView.ivCardBurger)
            .load(data.burgerImageUrl)
            .into(itemView.ivCardBurger)
    }

}