package com.lee.rxbindingpj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.ObservableField
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import com.lee.rxbindingpj.databinding.ActivityMainBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import java.util.concurrent.TimeUnit

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var mCompositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        addListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(::mCompositeDisposable.isInitialized){
            mCompositeDisposable.clear()
        }
    }

    private fun addListeners() {
        with(binding){
            mCompositeDisposable = CompositeDisposable()
            val inputEditTextDisposable = inputEditText.textChanges()
                .debounce(1000 , TimeUnit.MILLISECONDS , AndroidSchedulers.mainThread())
                .subscribe({ verificationTextView.text = String.format("입력된 글자는 : %s" , it) } , {it.printStackTrace()})

            mCompositeDisposable.addAll(inputEditTextDisposable)
        }
    }
}