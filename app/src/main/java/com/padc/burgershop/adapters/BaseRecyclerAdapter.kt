package com.padc.burgershop.adapters

import androidx.recyclerview.widget.RecyclerView
import com.padc.burgershop.viewholders.BaseViewHolder

abstract class BaseRecyclerAdapter<T:BaseViewHolder<W>,W> : RecyclerView.Adapter<T>() {

    protected var mData : ArrayList<W> = arrayListOf()

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bindData(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun setNewData(newData : List<W>){
        if(newData.isEmpty()){
            mData.clear()
        }else{
            mData= ArrayList(newData)
        }
        notifyDataSetChanged()
    }
}