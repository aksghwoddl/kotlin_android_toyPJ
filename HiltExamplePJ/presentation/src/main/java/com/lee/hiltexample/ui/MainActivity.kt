package com.lee.hiltexample.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.lee.domain.common.NetworkResult
import com.lee.hiltexample.databinding.ActivityMainBinding
import com.lee.hiltexample.ui.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val viewModel : MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        observeData()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getBeachInfo()
    }

    private fun observeData() {
        with(viewModel){
            lifecycleScope.launch{
                repeatOnLifecycle(Lifecycle.State.STARTED){ // 여러개의 Flow를 collect 할때 사용
                    launch {
                        networkResult.collect{ result ->
                            when(result){
                                is NetworkResult.Success -> setBeachCongestionList(result.data)
                                is NetworkResult.Failure -> onError(result.code.toString())
                                is NetworkResult.Exception -> onError(result.errorMessage)
                                is NetworkResult.Loading -> {
                                    // Noting to do
                                }
                            }
                        }
                    }
                }

                /*networkResult.flowWithLifecycle(lifecycle , Lifecycle.State.STARTED).collect{ result -> // 하나의 Flow만 collect할때 사용
                    when(result){
                        is NetworkResult.Success -> setBeachCongestionList(result.data)
                        is NetworkResult.Failure -> onError(result.code.toString())
                        is NetworkResult.Exception -> onError(result.errorMessage)
                        is NetworkResult.Loading -> {
                            // Noting to do
                        }
                    }
                }*/
            }

            beachCongestionList.observe(this@MainActivity){ list ->
                Log.d(TAG, "observeData: $list")
            }

            toastMessage.observe(this@MainActivity){ message ->
                Log.d(TAG, "observeData: $message")
            }
        }
    }
}