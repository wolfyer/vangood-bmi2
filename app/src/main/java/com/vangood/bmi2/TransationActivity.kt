package com.vangood.bmi2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import kotlin.concurrent.thread

class TransationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transation)
        thread {
            val json = URL("https://atm201605.appspot.com/h").readText()
            val gson = Gson()
            val transactions = gson.fromJson(json,Array<Transation>::class.java).toList()
            transactions.forEach {
                println(it) //+data class
            }

            /*val transactions = mutableListOf<Transation>()
            val array = JSONArray(json)
            for (i in 0 until array.length()) {
                val obj :JSONObject = array.getJSONObject(i)
                //println(obj.getInt("amount"))
                val amount = obj.getInt("amount")
                val account = obj.getString("account")
                val date = obj.getString("date")
                val type = obj.getInt("type")
                val tran = Transation(account,date,amount,type)
                transactions.add(tran)
            }*/
        }
    }
}
data class Transation(val account:String,val date:String,val  amount:Int,var type:Int){

}