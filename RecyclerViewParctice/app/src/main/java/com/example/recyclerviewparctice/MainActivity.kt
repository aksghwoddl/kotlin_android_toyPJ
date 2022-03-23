package com.example.recyclerviewparctice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG : String = "로그"

    // 데이터를 담을 그릇 즉 배열
    var modelList = ArrayList<MyModel>()
    private lateinit var recyclerViewAdapter : RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG , "MainActivity - onCreate")

        Log.d(TAG , "MainActivity - ArrayList만들어지기 전 ${this.modelList.size}")

        for(i in 1..10){
            var myModel = MyModel(name = "devYo ${i}" , profileImage = "peach.png")
            this.modelList.add(myModel)
        }
        Log.d(TAG , "MainActivity - ArrayList만들어지기 후 ${this.modelList.size}")

        // Adapter 인스턴스 생성
        recyclerViewAdapter = RecyclerAdapter()
        recyclerViewAdapter.submitList(this.modelList)
        recyclerView.apply {
            //
            layoutManager = LinearLayoutManager(this@MainActivity , LinearLayoutManager.VERTICAL , false)

            adapter = recyclerViewAdapter
        }
    }
}