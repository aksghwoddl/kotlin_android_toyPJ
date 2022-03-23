package com.example.recyclerviewparctice

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter : RecyclerView.Adapter<ViewHolder>() {
    val TAG : String = "로그"
    private var modelList = ArrayList<MyModel>()

    //뷰 홀더가 생성 되었을
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //뷰를 생성하는 동작
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_recycler_item , parent ,false))
    }
    //뷰 홀더가 bind 되었을때
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG , "RecyclerAdapter - onBindViewHolder() called / position : ${position}")
        holder.bind(this.modelList[position]) // 뷰를 묶는 동작
        holder.itemView.setOnClickListener{
            Toast.makeText(App.instance , "${position+1}번째 클릭됨!" , Toast.LENGTH_SHORT).show()
        }
    }
    //목록의 아이템 갯수
    override fun getItemCount(): Int {
        return this.modelList.size
    }

    fun submitList(modelList : ArrayList<MyModel>){
        this.modelList = modelList
    }

}