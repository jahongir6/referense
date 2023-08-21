package com.example.referense.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.referense.db.MyConstant.CUSTOMER_ADDRESS
import com.example.referense.db.MyConstant.CUSTOMER_ID
import com.example.referense.db.MyConstant.CUSTOMER_NAME
import com.example.referense.db.MyConstant.CUSTOMER_NUMBER
import com.example.referense.db.MyConstant.CUSTOMER_TABLE
import com.example.referense.db.MyConstant.DB_NAME
import com.example.referense.db.MyConstant.EMPLOYEE_ID
import com.example.referense.db.MyConstant.EMPLOYEE_NAME
import com.example.referense.db.MyConstant.EMPLOYEE_NUMBER
import com.example.referense.db.MyConstant.EMPLOYEE_TABLE
import com.example.referense.db.MyConstant.ORDER_CUTOMER_ID
import com.example.referense.db.MyConstant.ORDER_DATE
import com.example.referense.db.MyConstant.ORDER_EMPLOYEE_ID
import com.example.referense.db.MyConstant.ORDER_ID
import com.example.referense.db.MyConstant.ORDER_NAME
import com.example.referense.db.MyConstant.ORDER_PRICE
import com.example.referense.db.MyConstant.ORDER_TABLE
import com.example.referense.db.MyConstant.VERSION
import com.example.referense.models.MyCustomer
import com.example.referense.models.MyEmployee
import com.example.referense.models.MyOrder

class MyDbHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, VERSION),
    MyDbInterface {
    override fun onCreate(db: SQLiteDatabase?) {
        val queryCutomer =
            "create table ${MyConstant.CUSTOMER_TABLE} ($CUSTOMER_ID integer  primary key autoincrement ,$CUSTOMER_NAME TEXT NOT NULL,$CUSTOMER_NUMBER TEXT NOT NULL,$CUSTOMER_ADDRESS TEXT NOT NULL )"
        val quertEmployee =
            "create table ${MyConstant.EMPLOYEE_TABLE} ($EMPLOYEE_ID integer primary key autoincrement ,$EMPLOYEE_NAME TEXT NOT NULL,$EMPLOYEE_NUMBER TEXT NOT NULL)"
        val queryOrders =
            "create table ${MyConstant.ORDER_TABLE} ($ORDER_ID integer primary key autoincrement , $ORDER_NAME TEXT NOT NULL,$ORDER_DATE TEXT NOT NULL,$ORDER_PRICE integer NOT NULL,$ORDER_EMPLOYEE_ID INTEGER NOT NULL,$ORDER_CUTOMER_ID INTEGER NOT NULL,FOREIGN KEY ($ORDER_EMPLOYEE_ID) REFERENCES $EMPLOYEE_TABLE ($EMPLOYEE_ID), FOREIGN KEY ($ORDER_CUTOMER_ID) REFERENCES $CUSTOMER_TABLE ($CUSTOMER_ID))"
        db?.execSQL(queryCutomer)
        db?.execSQL(quertEmployee)
        db?.execSQL(queryOrders)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    override fun addCustomer(myCustomer: MyCustomer) {
        var database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(CUSTOMER_NAME, myCustomer.name)
        contentValues.put(CUSTOMER_NUMBER, myCustomer.address)
        contentValues.put(CUSTOMER_ADDRESS, myCustomer.address)
        database.insert(CUSTOMER_TABLE, null, contentValues)
        database.close()
    }

    override fun getAllCustomer(): List<MyCustomer> {
        val database = this.readableDatabase
        val list = ArrayList<MyCustomer>()
        val query = "select * from $CUSTOMER_TABLE"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                list.add(
                    MyCustomer(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                    )
                )
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun addEmployee(myEmployee: MyEmployee) {
        var database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(EMPLOYEE_NAME, myEmployee.name)
        contentValues.put(EMPLOYEE_NUMBER, myEmployee.number)
        database.insert(EMPLOYEE_TABLE, null, contentValues)
        database.close()
    }

    override fun getAllEmployee(): List<MyEmployee> {
        val database = this.readableDatabase
        val list = ArrayList<MyEmployee>()
        val query = "select * from $EMPLOYEE_TABLE"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                list.add(
                    MyEmployee(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                    )
                )
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun addOrders(myOrder: MyOrder) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ORDER_NAME, myOrder.name)
        contentValues.put(ORDER_DATE, myOrder.date)
        contentValues.put(ORDER_PRICE, myOrder.price)
        contentValues.put(ORDER_EMPLOYEE_ID, myOrder.myEmployee?.id)
        contentValues.put(ORDER_CUTOMER_ID, myOrder.myCustomer?.id)
        database.insert(ORDER_TABLE, null, contentValues)
        database.close()
    }

    override fun getAllOrders(): List<MyOrder> {
        val list = ArrayList<MyOrder>()
        val database = this.readableDatabase
        val query = "select * from $ORDER_TABLE"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                list.add(
                    MyOrder(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(3),
                        getAllEmployeeById(cursor.getInt(4)),
                        gettAllCustomerById(cursor.getInt(5)),
                        cursor.getString(2)

                    )
                )
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun getAllEmployeeById(id: Int): MyEmployee {
        val database = this.readableDatabase
        val cursor = database.query(
            EMPLOYEE_TABLE,
            arrayOf(EMPLOYEE_ID, EMPLOYEE_NAME, EMPLOYEE_NUMBER),
            "$EMPLOYEE_ID = ?",
            arrayOf(id.toString()),
            null, null, null
        )
        cursor.moveToFirst()
        val myEmployee = MyEmployee(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2)
        )
        return myEmployee
    }

    override fun gettAllCustomerById(id: Int): MyCustomer {
        val database = readableDatabase
        val cursor = database.query(
            CUSTOMER_TABLE,
            arrayOf(CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_NUMBER, CUSTOMER_ADDRESS),
            "$CUSTOMER_ID = ?",
            arrayOf(id.toString()),
            null, null, null
        )
        cursor.moveToFirst()
        val myCustomer = MyCustomer(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3)
        )
        return myCustomer
    }


}