package com.padc.burgershop.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView : View) : RecyclerView.ViewHolder(itemView) {
    protected var mData : T?=null
    abstract fun bindData(data : T)
}