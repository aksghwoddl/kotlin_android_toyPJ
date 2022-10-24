package com.lee.mycalculator

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lee.mycalculator.databinding.ActivityMainBinding
import com.lee.mycalculator.utils.CalculatorUtils.Companion.getPriority
import com.lee.mycalculator.utils.CalculatorUtils.Companion.isOperator
import java.util.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding : ActivityMainBinding
    private var inputs = ""
    private var previousResult = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate()")
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        addListeners()
    }

    private fun addListeners() { // 리스너 추가 함수
        Log.d(TAG, "addListeners()")
        with(binding){
            num1.setOnClickListener {
                inputs += "1"
                tvInputs.text = inputs
            }
           num2.setOnClickListener {
                inputs += "2"
                tvInputs.text = inputs
            }
            num3.setOnClickListener {
                inputs += "3"
                tvInputs.text = inputs
            }
            num4.setOnClickListener {
                inputs += "4"
                tvInputs.text = inputs
            }
           num5.setOnClickListener {
                inputs += "5"
                tvInputs.text = inputs
            }
            num6.setOnClickListener {
                inputs += "6"
                tvInputs.text = inputs
            }
            num7.setOnClickListener {
                inputs += "7"
                tvInputs.text = inputs
            }
            num8.setOnClickListener {
                inputs += "8"
                tvInputs.text = inputs
            }
            num9.setOnClickListener {
                inputs += "9"
                tvInputs.text = inputs
            }
            num0.setOnClickListener {
                inputs += "0"
                tvInputs.text = inputs
            }

            operatorDot.setOnClickListener {
                inputs += "."
                tvInputs.text = inputs
            }

            operatorDivide.setOnClickListener {
                if(inputs.isEmpty()){
                    if(previousResult.isEmpty()){
                        Toast.makeText(this@MainActivity , "숫자를 먼저 입력해주세요!!" , Toast.LENGTH_SHORT).show()
                    }
                    else {
                        inputs += "$previousResult/"
                        tvInputs.text = inputs
                    }

                }
                else {
                    if(isOperator(inputs.last().toString())){
                        Toast.makeText(this@MainActivity , "연산자 다음에는 다시 연산자가 올 수 없습니다!!" , Toast.LENGTH_SHORT).show()
                    } else {
                        inputs += "/"
                        tvInputs.text = inputs
                    }
                }

            }

            operatorMultiple.setOnClickListener {
                if(inputs.isEmpty()){
                    if(previousResult.isEmpty()){
                        Toast.makeText(this@MainActivity , "숫자를 먼저 입력해주세요!!" , Toast.LENGTH_SHORT).show()
                    }
                    else {
                        inputs += "$previousResult*"
                        tvInputs.text = inputs
                    }
                }
                else {
                    if(isOperator(inputs.last().toString())){
                        Toast.makeText(this@MainActivity , "연산자 다음에는 다시 연산자가 올 수 없습니다!!" , Toast.LENGTH_SHORT).show()
                    } else {
                        inputs += "*"
                        tvInputs.text = inputs
                    }
                }
            }

            operatorPlus.setOnClickListener {
                if(inputs.isEmpty()){
                    if(previousResult.isEmpty()){
                        Toast.makeText(this@MainActivity , "숫자를 먼저 입력해주세요!!" , Toast.LENGTH_SHORT).show()
                    } else {
                        inputs += "$previousResult+"
                        tvInputs.text = inputs
                    }

                } else {
                    if(isOperator(inputs.last().toString())){
                            Toast.makeText(this@MainActivity , "연산자 다음에는 다시 연산자가 올 수 없습니다!!" , Toast.LENGTH_SHORT).show()
                    } else {
                        inputs += "+"
                        tvInputs.text = inputs
                    }
                }
            }

            operatorSubtract.setOnClickListener {
                if(inputs.isEmpty()){
                    if(previousResult.isNotEmpty()){
                        inputs = "$previousResult-"
                        tvInputs.text = inputs
                    } else {
                        inputs += "-"
                        tvInputs.text = inputs
                    }
                } else{
                    if(isOperator(inputs.last().toString())) {
                        Toast.makeText(
                            this@MainActivity,
                            "연산자 다음에는 다시 연산자가 올 수 없습니다!!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        inputs += "-"
                        tvInputs.text = inputs
                    }
                }
            }

            btnErase.setOnClickListener {
                if(inputs.isNotEmpty()){
                    inputs =  inputs.substring(0 , inputs.lastIndex)
                    tvInputs.text = inputs
                }
            }

            operatorEqual.setOnClickListener {
                if(inputs.isNotEmpty()){
                    calculateWithPostfix(inputs)
                }
            }
        }
    }

    private fun calculateWithPostfix(value : String){ // 후위 표기로 바꾸는 함수
        Log.d(TAG, "calculateWithPostfix: $value ")

        val postFix = mutableListOf<String>()
        val opStack = Stack<String>()
        var num = ""

        value.forEachIndexed { index, c ->
            if(isOperator(c.toString()) && index != 0){
                if(num != ""){
                    postFix.add(num)
                    num = ""
                }
                if(opStack.isEmpty()){ // operator가 비어있을 경우 무조건 stack에 추가
                    opStack.push(c.toString())
                } else {
                    if(getPriority(opStack.peek()) < getPriority(c.toString())){
                        opStack.push(c.toString())
                    } else {
                        postFix.add(opStack.pop())
                        opStack.push(c.toString())
                    }
                }
            } else {
                num += c.toString()
            }
        }
        if(num != ""){
            postFix.add(num)
        }
        while(!opStack.isEmpty()){ // 모든 문자열 체크가 끝나고 후위연산식에 operator를 저장해놓은 stack의 값을 저장
            postFix.add(opStack.pop())
        }
        Log.d(TAG, "calculateWithPostfix: $postFix")
        calculate(postFix)
    }

    private fun calculate(postfix : MutableList<String>) { // 후위 연산식으로 계산하는 함수
        val numStack = Stack<String>()
        Log.d(TAG, "calculate()")

        postfix.forEach {
            if (isOperator(it)){
                var result = ""
                val value1 = numStack.pop().toDouble()
                Log.d(TAG, "calculate: value1 $value1")
                val value2 = numStack.pop().toDouble()
                Log.d(TAG, "calculate: value2 $value2")
                when(it){
                    "+" -> { result = (value2 + value1).toString() }
                    "-" -> { result = (value2 - value1).toString() }
                    "*" -> { result = (value2 * value1) .toString() }
                    "/" -> { result = (value2 / value1).toString() }
                }
                Log.d(TAG, "calculate: result = $result")
                Log.d(TAG, "calculate: numStack $numStack")
                numStack.push(result)
            }
            else {
                numStack.push(it)
            }
            Log.d(TAG, "calculate: numStack $numStack")
        }

        val calculateResult = numStack.pop().toDouble() // 계산 결과
        Log.d(TAG, "calculate: $calculateResult")

        val resultToArray = calculateResult.toString().split(".") // 계산 결과의 리턴값을 정하기 위한 배열
        Log.d(TAG, "calculate: $resultToArray")

        //  정수 , 실수 여부에 따라 리턴값을 다르게줌
        if(resultToArray[1] == "0"){
            with(binding){
                tvResult.text = resultToArray[0]
                tvInputs.text = ""
                inputs = ""
                previousResult = resultToArray[0]
            }
        } else {
            with(binding){
                val formattedString = String.format("%.2f" , calculateResult)
                tvResult.text = formattedString
                tvInputs.text = ""
                inputs = ""
                previousResult = formattedString
            }
        }
    }
}