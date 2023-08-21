package com.example.referense.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.example.referense.R
import com.example.referense.adapter.MyCustomerAdapter
import com.example.referense.databinding.FragmentEmployeeBinding
import com.example.referense.databinding.ItemDialogBinding
import com.example.referense.db.MyDbHelper
import com.example.referense.models.MyCustomer
import com.example.referense.models.MyEmployee

class EmployeeFragment : Fragment() {
    private lateinit var binding: FragmentEmployeeBinding
    private lateinit var myCustomerAdapter: MyCustomerAdapter<MyEmployee>
    private lateinit var myDbHelper: MyDbHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmployeeBinding.inflate(layoutInflater)
        myDbHelper = MyDbHelper(binding.root.context)
        myCustomerAdapter = MyCustomerAdapter(myDbHelper.getAllEmployee())
        binding.rv.adapter = myCustomerAdapter
        binding.apply {
            btnAdd.setOnClickListener {
                val dialog = AlertDialog.Builder(binding.root.context).create()
                val itemDialogBinding = ItemDialogBinding.inflate(layoutInflater)
                dialog.setView(itemDialogBinding.root)
                itemDialogBinding.edtNumber.visibility = View.VISIBLE
                itemDialogBinding.spinnerEmployee.visibility = View.GONE
                itemDialogBinding.spinnerCustomer.visibility = View.GONE
                itemDialogBinding.btnSave.setOnClickListener {
                    val myEmployee = MyEmployee(
                        itemDialogBinding.edtName.text.toString(),
                        itemDialogBinding.edtNumber.text.toString()
                    )
                    myDbHelper.addEmployee(myEmployee)
                    dialog.cancel()
                }

                dialog.show()
            }
        }
        return binding.root
    }
}