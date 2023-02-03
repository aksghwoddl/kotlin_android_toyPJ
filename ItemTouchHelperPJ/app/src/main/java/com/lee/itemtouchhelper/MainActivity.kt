package com.lee.itemtouchhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.itemtouchhelper.adapter.ItemTouchHelperCallBack
import com.lee.itemtouchhelper.adapter.MainRecyclerViewAdapter
import com.lee.itemtouchhelper.data.RecyclerItem
import com.lee.itemtouchhelper.databinding.ActivityMainBinding
import com.lee.itemtouchhelper.viewmodel.MainActivityViewModel
import com.lee.itemtouchhelper.viewmodel.factory.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : MainActivityViewModel
    private lateinit var mainRecyclerViewAdapter : MainRecyclerViewAdapter
    private lateinit var items : ArrayList<RecyclerItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        initRecyclerView()
        viewModel = ViewModelProvider(this , MainViewModelFactory())[MainActivityViewModel::class.java]
        generateItems()
    }

    override fun onStart() {
        super.onStart()
        observeData()
    }

    private fun generateItems() {
        items = arrayListOf()
        for(i in 1.. 10){
            val item = RecyclerItem("${i}번째" , R.mipmap.ic_launcher)
            items.add(item)
        }
        viewModel.setList(items)
    }

    private fun initRecyclerView() {
        mainRecyclerViewAdapter = MainRecyclerViewAdapter()
        binding.mainRecyclerView.run {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainRecyclerViewAdapter
        }
        val itemTouchHelperCallBack = ItemTouchHelperCallBack(mainRecyclerViewAdapter)
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallBack)

        itemTouchHelper.attachToRecyclerView(binding.mainRecyclerView)
    }

    private fun observeData(){
        with(viewModel){
            list.observe(this@MainActivity){
                mainRecyclerViewAdapter.submitList(it)
            }
        }
    }
}