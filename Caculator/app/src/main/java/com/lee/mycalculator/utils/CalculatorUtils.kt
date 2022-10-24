package com.lee.mycalculator.utils

class CalculatorUtils {
    companion object{
        fun isOperator(c : String) : Boolean {
            return when(c){
                "+" , "-" , "*" , "/" -> true
                else -> false
            }
        }

        fun getPriority(value : String) : Int {
            return when(value){
                "-" , "+" -> 1
                "*" , "/" -> 2
                else -> -1
            }
        }
    }
}