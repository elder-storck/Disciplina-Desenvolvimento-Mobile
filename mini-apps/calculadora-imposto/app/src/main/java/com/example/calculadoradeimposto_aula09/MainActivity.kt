package com.example.calculadoradeimposto_aula09

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.calculadoradeimposto_aula09.databinding.ActivityMainBinding
import com.example.calculadoradeimposto_aula09.R

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalculate.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if(view.id == R.id.buttonCalculate){
            val salary = binding.editSalary.text.toString().toFloatOrNull() ?: 0f
            val numberOfDependents = binding.editDependents.text.toString().toFloatOrNull() ?: 0f
            val healthAndEducationExpenses = binding.editExpenses.text.toString().toFloatOrNull() ?: 0f

            var taxRate: Double = 0.0         // alíquota
            var deduction: Double = 0.0       // dedução
            var taxableIncome = salary - (numberOfDependents * 189.59) - healthAndEducationExpenses

            if(taxableIncome in 2428.81..2826.65){
                taxRate = 0.075
                deduction = 182.16
            }else if(taxableIncome in 2826.66..3751.05){
                taxRate = 0.15
                deduction = 394.16
            }else if(taxableIncome in 3751.06..4664.68){
                taxRate = 0.225
                deduction = 675.49
            }else if(taxableIncome > 4664.68){
                taxRate = 0.275
                deduction = 908.73
            }

            val incomeTax = taxableIncome*taxRate - deduction
            binding.results.text = String.format("%.2f", incomeTax)

            Toast.makeText(applicationContext, R.string.success_msg, Toast.LENGTH_SHORT).show()

        }else{
            Toast.makeText(applicationContext, R.string.error_button_msg, Toast.LENGTH_SHORT).show()
        }
    }
}