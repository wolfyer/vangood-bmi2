package com.vangood.bmi2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.vangood.bmi2.databinding.ActivityTransationBinding
import com.vangood.bmi2.databinding.RowRecycleviewBinding
import java.net.URL
import kotlin.concurrent.thread

class TransationActivity : AppCompatActivity() {
    lateinit var binding : ActivityTransationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTransationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.recycler.hasFixedSize()
        binding.recycler.layoutManager = LinearLayoutManager(this)
        thread {
            val json = URL("https://atm201605.appspot.com/h").readText()
            val gson = Gson()
            val transactions = gson.fromJson(json,Array<Transation>::class.java).toList()
            transactions.forEach {
                println(it) //+data class
            }

            //runOnUiThread { Toast.makeText(this,"Testing",Toast.LENGTH_LONG).show() }

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
            runOnUiThread{
                binding.recycler.adapter = object : RecyclerView.Adapter<TranViewHolder>() {
                    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranViewHolder {
                        val binding = RowRecycleviewBinding.inflate(layoutInflater,parent,false)
                        return TranViewHolder(binding)
                    }

                    override fun onBindViewHolder(holder: TranViewHolder, position: Int) {
                        val tran = transactions.get(position)
                        holder.account.setText(tran.account.toString())
                        holder.date.setText(tran.date.toString())
                    }

                    override fun getItemCount(): Int {
                        return transactions.size
                    }


                }
            }


        }

    }


    inner class TranViewHolder(val binding: RowRecycleviewBinding):
        RecyclerView.ViewHolder(binding.root){
        val account = binding.rAccount
        val date = binding.rDate
        val amount = binding.rAmount
        val type = binding.rType

    }
}
data class Transation(val account:String,val date:String,val  amount:Int,var type:Int){

}