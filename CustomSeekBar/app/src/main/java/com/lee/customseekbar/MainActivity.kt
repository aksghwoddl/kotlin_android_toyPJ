package com.lee.customseekbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.lee.customseekbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        binding.circleSb.setProgressWithAnimation(65f)

        binding.sb1.setOnSeekBarChangeListener(object : OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar : SeekBar?, progress : Int, changeFromUser : Boolean) {
                with(binding.circleSb){
                    setProgressValue(progress.toFloat())
                    setProgressWithAnimation(progress.toFloat())
                    backgroundProgressBarWidth = progress.toFloat()
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }
}