package com.example.referense.models

import java.text.SimpleDateFormat
import java.util.*

class MyOrder {
    var id: Int? = null
    var name: String? = null
    var date: String? = null
    var price: Int? = null
    var myEmployee: MyEmployee? = null
    var myCustomer: MyCustomer? = null

    constructor(
        id: Int?,
        name: String?,
        price: Int?,
        myEmployee: MyEmployee?,
        myCustomer: MyCustomer?,
        date: String = SimpleDateFormat("dd:MM:yyyy").format(Date())
    ) {
        this.id = id
        this.name = name
        this.date = date
        this.price = price
        this.myEmployee = myEmployee
        this.myCustomer = myCustomer
    }

    constructor(
        name: String?,
        price: Int?,
        myEmployee: MyEmployee?,
        myCustomer: MyCustomer?,
        date: String = SimpleDateFormat("dd:MM:yyyy").format(Date())
    ) {
        this.name = name
        this.date = date
        this.price = price
        this.myEmployee = myEmployee
        this.myCustomer = myCustomer
    }

    constructor()
}