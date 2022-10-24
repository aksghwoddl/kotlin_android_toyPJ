package com.lee.calculateedittext

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.core.widget.addTextChangedListener
import com.lee.calculateedittext.databinding.ActivityMainBinding
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate()")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        addListeners()
    }

    private fun addListeners() { // 모든 Listener를 초기화 하는 함수
        Log.d(TAG, "addListeners()")
        with(binding){
            addEditTextListeners(valueEditText1)
            addEditTextListeners(valueEditText2)
            addEditTextListeners(valueEditText3)
        }
    }

    private fun addEditTextListeners(view : EditText){ //EditText에 대한 Lister들 추가
        view.addTextChangedListener(object : TextWatcher{
            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(text?.isDigitsOnly() == true){ // 문자열에 혹시나 일반 문자가 포함될 경우에 대한 예외 처리
                    calculateEditText(false)
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun afterTextChanged(p0: Editable?) { }
        })

        view.onFocusChangeListener = FocusChangedListener()

        view.filters = arrayOf(InputFilter { source, _,  _, _, _, _ -> // 숫자가 아닌경우 입력이 제한되고 Toast 띄움
            val numRegex = "^[0-9]*$"
            val numPattern = Pattern.compile(numRegex)
            if(numPattern.matcher(source).matches()){
                return@InputFilter source
            }
            Toast.makeText(this@MainActivity , "숫자만 입력 가능합니다!" , Toast.LENGTH_SHORT).show()
            source.dropLast(1)
        })
    }

    /**
     * Focus가 이동되거나 문자열이 변경되면 각 EditText의 값을 더한다.
     * isFocus를 통해 FocusChangedListener에 의한 호출인지 판별하여 동작을 달리한다.
     **/
    @SuppressLint("SetTextI18n")
    private fun calculateEditText(isFocusListener : Boolean) { //
        with(binding){
            val value1 = if(valueEditText1.text.isEmpty()){
                0
            } else {
                valueEditText1.text.toString().toLong()
            }

            val value2 = if(valueEditText2.text.isEmpty()){
                0
            } else {
                valueEditText2.text.toString().toLong()
            }

            val value3 = if(valueEditText3.text.isEmpty()){
                0
            } else {
                valueEditText3.text.toString().toLong()
            }

            val sum = value1 + value2 + value3
            if(isFocusListener){
                resultFocusTextView.text = "Focused : $sum"
            } else {
                resultTextView.text = "Text Changed : $sum"
            }

        }
    }

    inner class FocusChangedListener : OnFocusChangeListener{ //FocusChangedListener는 공통으로 인터페이스를 구현하여 사용
        override fun onFocusChange(view: View?, hasFocus : Boolean) {
            if(view is EditText){
                if(view.text.isNotEmpty() && !hasFocus){
                    if(view.text.toString().isDigitsOnly()) {
                        calculateEditText(true)
                    }
                }
            }
        }
    }
}