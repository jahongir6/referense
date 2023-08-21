package com.example.referense.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.referense.adapter.MyOrderAdapter
import com.example.referense.databinding.FragmentEmployeeBinding
import com.example.referense.databinding.ItemDialogBinding
import com.example.referense.db.MyDbHelper
import com.example.referense.models.MyEmployee
import com.example.referense.models.MyOrder

class OrderFragment : Fragment() {
    private lateinit var binding: FragmentEmployeeBinding
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var orderAdapter: MyOrderAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmployeeBinding.inflate(layoutInflater)
        myDbHelper = MyDbHelper(binding.root.context)
        orderAdapter = MyOrderAdapter(myDbHelper.getAllOrders())
        binding.rv.adapter = orderAdapter
        binding.btnAdd.setOnClickListener {
            val dialog = AlertDialog.Builder(binding.root.context).create()
            val itemDialogBinding = ItemDialogBinding.inflate(layoutInflater)
            dialog.setView(itemDialogBinding.root)
            dialog.show()
            itemDialogBinding.apply {
                val emList = ArrayList<String>()
                val employeeList = myDbHelper.getAllEmployee()
                for (myEmplyee in employeeList) {
                    emList.add(myEmplyee.name!!)
                }
                spinnerEmployee.adapter =
                    ArrayAdapter(binding.root.context, android.R.layout.simple_list_item_1, emList)

                val custList = ArrayList<String>()
                val customerList = myDbHelper.getAllCustomer()
                for (myCustomer in customerList) {
                    custList.add(myCustomer.name!!)
                }
                spinnerCustomer.adapter = ArrayAdapter(
                    binding.root.context,
                    android.R.layout.simple_list_item_1,
                    custList
                )
                edtNumber.visibility = View.VISIBLE
                btnSave.setOnClickListener {
                    val myOrder = MyOrder(
                        edtName.text.toString(),
                        edtNumber.text.toString().toInt(),
                        employeeList[spinnerEmployee.selectedItemPosition],
                        customerList[spinnerCustomer.selectedItemPosition]
                    )
                    myDbHelper.addOrders(myOrder)
                    Toast.makeText(context, "saqlandi", Toast.LENGTH_SHORT).show()
                    dialog.cancel()
                }
            }
        }
        return binding.root
    }
}