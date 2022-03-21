package com.example.lottieanimationtutorial

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG : String = "로그"
    var isLiked : Boolean = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        like_button.setOnClickListener {
            Log.d(TAG , "MainActivity - onResume 좋아요 버튼 눌렸습니다! ${isLiked}")
            // 클릭 리스너 함수
            clickLikeButton()
        }
    }
    fun clickLikeButton() {
        if(!isLiked){
            //좋아요 누르지 않은 상태
            //ofFloat은 실행할 범위를 나타낸다. (0f = 0% , 0.5f = 50%)
            //setDuration은 애니메이션이 실행되는 시간을 설정한다.태
            //animator.start를 통해 애니메이션을 실행
            val animator = ValueAnimator.ofFloat(0f, 0.5f).setDuration(1000)
            isLiked = true
            animator.addUpdateListener {
                //updateListener를 통해 애니메이션이 갱신되도록 해준다.
                //getAnimateValue 현재 애니메이션이 실행되는 구간을 Object값으로 반환 해주는 함수
                //setProgress는 float값을 인자로 받기에 해당 값을 float값으로 캐스팅 해주었다.
                like_button.setProgress(animator.getAnimatedValue() as Float)
            }
            animator.start()

        }
        else{
            //좋아요 누른 상태
            val animator = ValueAnimator.ofFloat(0.5f, 1.0f).setDuration(200)
            isLiked = false
            animator.addUpdateListener {
                like_button.setProgress(animator.getAnimatedValue() as Float)
            }
            animator.start()
        }
    }
}