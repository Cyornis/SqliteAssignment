package com.example.sqliteassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    //method to save record in database
    fun saveRecord(view: View) {
        val id = findViewById<EditText>(R.id.u_id)
        val name = findViewById<EditText>(R.id.u_name)
        val email = findViewById<EditText>(R.id.u_email)

        val uId = id.text.toString()
        val uName = name.text.toString()
        val uEmail = email.text.toString()

        val dataBaseHandler = DataBaseHandler(this)
        val status =
            dataBaseHandler.addEmployee(EmpModelClass(Integer.parseInt(uId), uName, uEmail))

        Toast.makeText(applicationContext,"record save", Toast.LENGTH_LONG).show()

        id.text.clear()
        name.text.clear()
        email.text.clear()

    }

    //method to read records from database in ListView
    fun viewRecord() {

        val dataBaseHandler = DataBaseHandler(this)
        val emp: List<EmpModelClass> = dataBaseHandler.viewEmployee()
        val empArrayId = Array<String>(emp.size) { "0" }
        val empArrayName = Array<String>(emp.size) { "null" }
        val empArrayEmail = Array<String>(emp.size) { "null" }
        var index = 0

        val listView = findViewById<ListView>(R.id.listView)


        for (e in emp) {

            empArrayId[index] = e.userId.toString()
            empArrayName[index] = e.userName
            empArrayEmail[index] = e.userEmail

            index++
        }
     val myListAdapter = MyListAdapter(this,empArrayId,empArrayName,empArrayEmail)
     listView.adapter = myListAdapter
    }
}