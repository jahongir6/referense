package com.example.referense.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.referense.databinding.ItemRvBinding
import com.example.referense.models.MyCustomer
import com.example.referense.models.MyEmployee

class MyCustomerAdapter<T>(var list: List<T>) :
    RecyclerView.Adapter<MyCustomerAdapter<T>.Vh>() {

    inner class Vh(val itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {
        fun onCustomer(myCustomer: MyCustomer) {
            itemRvBinding.tvName.text = myCustomer.name
            itemRvBinding.tvNumber.visibility = View.VISIBLE
            itemRvBinding.tvNumber.text = myCustomer.number
            itemRvBinding.tvAddress.visibility = View.VISIBLE
            itemRvBinding.tvAddress.text = myCustomer.address
            itemRvBinding.tvCustomer.visibility = View.GONE
            itemRvBinding.tvEmployee.visibility = View.GONE
        }

        fun onEmployee(myEmployee: MyEmployee) {
            itemRvBinding.tvName.text = myEmployee.name
            itemRvBinding.tvNumber.visibility = View.VISIBLE
            itemRvBinding.tvNumber.text = myEmployee.number
            itemRvBinding.tvCustomer.visibility = View.GONE
            itemRvBinding.tvEmployee.visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        try {
            val myEmployee: MyEmployee = list[position] as MyEmployee
            holder.onEmployee(myEmployee)
            holder
        } catch (e: Exception) {
            val myCustomer: MyCustomer = list[position] as MyCustomer
            holder.onCustomer(myCustomer)
        }
    }

    override fun getItemCount(): Int = list.size

}