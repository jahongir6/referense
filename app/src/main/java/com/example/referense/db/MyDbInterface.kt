package com.example.referense.db

import com.example.referense.models.MyCustomer
import com.example.referense.models.MyEmployee
import com.example.referense.models.MyOrder

interface MyDbInterface {
    fun addCustomer(myCustomer: MyCustomer)
    fun getAllCustomer(): List<MyCustomer>

    fun addEmployee(myEmployee: MyEmployee)
    fun getAllEmployee(): List<MyEmployee>

    fun addOrders(myOrder: MyOrder)
    fun getAllOrders(): List<MyOrder>

    fun getAllEmployeeById(id:Int):MyEmployee
    fun gettAllCustomerById(id: Int):MyCustomer
}