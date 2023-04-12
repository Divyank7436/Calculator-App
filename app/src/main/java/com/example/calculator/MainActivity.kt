package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    private var tvinput: TextView?=null
    var lastnumeric: Boolean= false
    var lastdot: Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvinput=findViewById(R.id.tvInput)
    }
    fun onDigit(view: View){
        tvinput?.append((view as Button).text)
        lastnumeric=true
        lastdot=false
    }
    fun onClear(view: View) {
        tvinput?.text=""
    }
    fun decimalpoint(view: View) {
        if(lastnumeric && !lastdot){
            tvinput?.append(".")
            lastnumeric=false
            lastdot=true
        }
    }
    fun onOperator(view: View){
        tvinput?.text?.let{
            if(lastnumeric && !isOperatorAdded(it.toString())){
                tvinput?.append((view as Button).text)
                lastnumeric=false
                lastdot=false
            }
        }
    }
    fun onEqual(view: View){
        if(lastnumeric){
            var tvValue=tvinput?.text.toString()
            var prefix=""
            try{
                if(tvValue.startsWith("-")){
                    prefix="-"
                    tvValue=tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                    val splitValue=tvValue.split("-")
                    var one=splitValue[0]
                    var two = splitValue[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }
                    tvinput?.text=toRemoveZero((one.toDouble()-two.toDouble()).toString())
                }
                if(tvValue.contains("+")){
                    val splitValue=tvValue.split("+")
                    var one=splitValue[0]
                    var two = splitValue[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }
                    tvinput?.text=toRemoveZero((one.toDouble()+two.toDouble()).toString())
                }
                if(tvValue.contains("*")){
                    val splitValue=tvValue.split("*")
                    var one=splitValue[0]
                    var two = splitValue[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }
                    tvinput?.text=toRemoveZero((one.toDouble()*two.toDouble()).toString())
                }
                if(tvValue.contains("/")){
                    val splitValue=tvValue.split("/")
                    var one=splitValue[0]
                    var two = splitValue[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }
                    tvinput?.text=toRemoveZero((one.toDouble()/two.toDouble()).toString())
                }
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    private fun toRemoveZero(result: String): String{
        var value = result
        if(result.contains(".0"))
            value=result.substring(0,result.length-2)
        return value
    }
    private fun isOperatorAdded(value : String): Boolean{
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("/") ||
                    value.contains("*") ||
                    value.contains("-") ||
                    value.contains("+")
        }
    }
}