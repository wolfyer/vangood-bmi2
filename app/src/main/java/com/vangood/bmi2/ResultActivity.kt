package com.vangood.bmi2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.vangood.bmi2.databinding.ActivityMainBinding
import com.vangood.bmi2.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private val TAG = ResultActivity::class.java.simpleName
    lateinit var binding : ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityResultBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        showBmi()
        binding.bOk.setOnClickListener {
            val name = binding.tnName.text.toString()
            val data = Intent()
            data.putExtra("NAME",name)
            setResult(RESULT_OK,)
            finish()

        }
    }


    private fun showBmi() {
        val bmi = intent.getFloatExtra("BMI_EXTRA", 0f)
        Log.d(TAG, "BMI_EXTRA: $bmi")
        binding.bmiDisplay.text = bmi.toString()
    }
}