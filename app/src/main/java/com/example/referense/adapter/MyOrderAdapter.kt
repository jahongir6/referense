package com.example.referense.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.referense.databinding.ItemRvBinding
import com.example.referense.models.MyOrder

class MyOrderAdapter(var list: List<MyOrder>) : RecyclerView.Adapter<MyOrderAdapter.Vh>() {

    inner class Vh(val itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {
        fun onBind(myOrder: MyOrder) {
            itemRvBinding.tvName.text = myOrder.name
            itemRvBinding.tvCustomer.text = myOrder.myCustomer?.name
            itemRvBinding.tvEmployee.text = myOrder.myEmployee?.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

}