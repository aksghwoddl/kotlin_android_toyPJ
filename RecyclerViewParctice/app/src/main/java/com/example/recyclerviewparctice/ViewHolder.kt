package com.example.recyclerviewparctice

import android.util.Log
import android.view.View

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_recycler_item.view.*

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val TAG : String = "로그"
    private val userNameTextView = itemView.user_name
    private val profileImageView = itemView.profile_img

    init {
        Log.d(TAG , "ViewHolder - init(0 called")
    }

    //데이터와 뷰를 묶는다.

    fun bind(myModel: MyModel){
        Log.d(TAG , "ViewHolder - bind")
        userNameTextView.text = myModel.name
        Glide.with(App.instance).load(R.drawable.peach).into(profileImageView)
    }
}