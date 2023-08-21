package com.example.referense.db

import android.os.Build.VERSION

object MyConstant {
    const val DB_NAME = "db_name"
    const val VERSION = 1

    const val CUSTOMER_TABLE = "customer_table"
    const val CUSTOMER_ID = "id"
    const val CUSTOMER_NAME = "name"
    const val CUSTOMER_NUMBER = "number"
    const val CUSTOMER_ADDRESS = "address"

    const val EMPLOYEE_TABLE = "employee_table"
    const val EMPLOYEE_NAME = "NAME"
    const val EMPLOYEE_ID = "id"
    const val EMPLOYEE_NUMBER = "number"

    const val ORDER_TABLE = "orders_table"
    const val ORDER_ID = "id"
    const val ORDER_NAME = "name"
    const val ORDER_PRICE = "price"
    const val ORDER_DATE = "date"
    const val ORDER_CUTOMER_ID = "customer_id"
    const val ORDER_EMPLOYEE_ID = "employee_id"


}