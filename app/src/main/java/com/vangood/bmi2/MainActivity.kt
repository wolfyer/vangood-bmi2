package com.vangood.bmi2

import android.content.ContentValues.TAG
import android.content.Context
import android.content.DialogInterface
import android.content.DialogInterface.*
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ActionMode
import com.vangood.bmi2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.simpleName
    val REQUEST_DISPLAY_BMI =16
    lateinit var binding: ActivityMainBinding
    var launcher = registerForActivityResult(NameContract()){
        name ->
        Log.d(TAG, ":$name ")

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate: ")
/*        binding.bHelp.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                TODO("Not yet implemented")
            }
        })*/
        binding.bHelp.setOnClickListener{
            Log.d("MainActivity","Need Help!")
        }


    }

    fun bmi(view: View){
        println("test123")
        Log.d("MainActivity","clicked")
        var weight = binding.edWeight.text.toString().toFloat()
        var height = binding.edHeight.text.toString().toFloat()
        var bmi = weight/(height*height)
        Log.d("MainActivity",bmi.toString())
        Toast.makeText(this,"Your bmi: $bmi",Toast.LENGTH_LONG)
            .show()
        /*val builder = AlertDialog.Builder(this)
        builder.setTitle("Hello")
        builder.setMessage("Your BMI is $bmi")
        builder.setPositiveButton("OK", null)
        val dialog = builder.create()
        dialog.show()*/
        AlertDialog.Builder(this)
            .setTitle("BMI")
            .setMessage("your bmi is $bmi")
            .setPositiveButton("OK"){d,w ->
                binding.edWeight.setText("")
                binding.edHeight.setText("")
            }

            //.show()
        binding.tvBmi.text = "Your bmi is $bmi"
        val intent = Intent(this,ResultActivity::class.java)
        intent.putExtra("BMI_EXTRA",bmi)
        //startActivity(intent)
        //startActivityForResult(intent,REQUEST_DISPLAY_BMI)
        launcher.launch(bmi)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "onActivityResult: ")
        if (requestCode == REQUEST_DISPLAY_BMI && resultCode == RESULT_OK){
            Log.d(TAG, "back from ResultActivity: ")
        }
    }
    class NameContract :ActivityResultContract<Float,String>(){
        override fun createIntent(context: Context, input: Float?): Intent {
            val intent = Intent(context,ResultActivity::class.java).putExtra("BMI_EXTRA",input)
            return intent
        }

        override fun parseResult(resultCode: Int, intent: Intent?): String {
            if(resultCode == RESULT_OK){
                val name = intent?.getStringExtra("NAME")
                return name!!
            }else{
                return "No name"
            }
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }


}