package com.example.myapplication54656494458548

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast

class updatedLevels : AppCompatActivity() {

    var UpAnim : Animation? = null
    var DownAnim : Animation? = null
    private var mBack : ImageButton? = null
    private var level1 : LinearLayout? = null
    private var level2 : LinearLayout? = null
    private var level3 : LinearLayout? = null
    private var level4 : LinearLayout? = null
    private var level5 : LinearLayout? = null
    private var level6 : LinearLayout? = null
    private var level7 : LinearLayout? = null
    private var level8 : LinearLayout? = null
    private var level9 : LinearLayout? = null
    private var level10 : LinearLayout? = null



    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_updated_levels)

        UpAnim = AnimationUtils.loadAnimation(this,R.anim.scale_up)
        DownAnim = AnimationUtils.loadAnimation(this,R.anim.scale_down)
        mBack = findViewById(R.id.btn_backBtn)
        mBack!!.setOnClickListener {
            val intent = Intent(this,fragmentActvity::class.java)
            startActivity(intent)
            finish()
        }

        level1 = findViewById(R.id.level1)
        level2 = findViewById(R.id.level2)
        level3 = findViewById(R.id.level3)
        level4 = findViewById(R.id.level4)
        level5 = findViewById(R.id.level5)
        level6 = findViewById(R.id.level6)
        level7 = findViewById(R.id.level7)
        level8 = findViewById(R.id.level8)
        level9 = findViewById(R.id.level9)
        level10 = findViewById(R.id.level10)



        level1!!.setOnTouchListener { View, motionEvent ->
            if(motionEvent.action == MotionEvent.ACTION_UP){
                level1?.startAnimation(UpAnim)
            }else if(motionEvent.action == MotionEvent.ACTION_DOWN){
                level1?.startAnimation(DownAnim)
                val intent = Intent(this, MainActivitySetQues::class.java)
                intent.putExtra("level", "1")
                startActivity(intent)
                finish()
            }

            return@setOnTouchListener true
        }
        level2!!.setOnTouchListener { View, motionEvent ->

                if (motionEvent.action == MotionEvent.ACTION_UP) {
                    level2?.startAnimation(UpAnim)
                } else if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                    level2?.startAnimation(DownAnim)
                    val intent = Intent(this, MainActivitySetQues::class.java)
                    intent.putExtra("level", "2")
                    startActivity(intent)
                    finish()
                }
            return@setOnTouchListener true
        }
        level3!!.setOnTouchListener { View, motionEvent ->
            if(motionEvent.action == MotionEvent.ACTION_UP){
                level3?.startAnimation(UpAnim)
            }else if(motionEvent.action == MotionEvent.ACTION_DOWN){
                level3?.startAnimation(DownAnim)
                val intent = Intent(this, MainActivitySetQues::class.java)
                intent.putExtra("level", "3")
                startActivity(intent)
                finish()
            }
            return@setOnTouchListener true
        }
        level4!!.setOnTouchListener { View, motionEvent ->
            if(motionEvent.action == MotionEvent.ACTION_UP){
                level4?.startAnimation(UpAnim)
            }else if(motionEvent.action == MotionEvent.ACTION_DOWN){
                level4?.startAnimation(DownAnim)
                val intent = Intent(this, MainActivitySetQues::class.java)
                intent.putExtra("level", "4")
                startActivity(intent)
                finish()
            }
            return@setOnTouchListener true
        }
        level5!!.setOnTouchListener { View, motionEvent ->
            if(motionEvent.action == MotionEvent.ACTION_UP){
                level5?.startAnimation(UpAnim)
            }else if(motionEvent.action == MotionEvent.ACTION_DOWN){
                level5?.startAnimation(DownAnim)
                val intent = Intent(this, MainActivitySetQues::class.java)
                intent.putExtra("level", "5")
                startActivity(intent)
                finish()
            }
            return@setOnTouchListener true
        }
        level6!!.setOnTouchListener { View, motionEvent ->
            if(motionEvent.action == MotionEvent.ACTION_UP){
                level6?.startAnimation(UpAnim)
            }else if(motionEvent.action == MotionEvent.ACTION_DOWN){
                level6?.startAnimation(DownAnim)
                val intent = Intent(this, MainActivitySetQues::class.java)
                intent.putExtra("level", "6")
                startActivity(intent)
                finish()
            }
            return@setOnTouchListener true
        }
        level7!!.setOnTouchListener { View, motionEvent ->
            if(motionEvent.action == MotionEvent.ACTION_UP){
                level7?.startAnimation(UpAnim)
            }else if(motionEvent.action == MotionEvent.ACTION_DOWN){
                level7?.startAnimation(DownAnim)
                val intent = Intent(this, MainActivitySetQues::class.java)
                intent.putExtra("level", "7")
                startActivity(intent)
                finish()
            }
           return@setOnTouchListener true
        }
        level8!!.setOnTouchListener { View, motionEvent ->
            if(motionEvent.action == MotionEvent.ACTION_UP){
                level8?.startAnimation(UpAnim)
            }else if(motionEvent.action == MotionEvent.ACTION_DOWN){
                level8?.startAnimation(DownAnim)
                val intent = Intent(this, MainActivitySetQues::class.java)
                intent.putExtra("level", "8")
                startActivity(intent)
                finish()
            }
            return@setOnTouchListener true
        }
        level9!!.setOnTouchListener { View, motionEvent ->
            if(motionEvent.action == MotionEvent.ACTION_UP){
                level9?.startAnimation(UpAnim)
            }else if(motionEvent.action == MotionEvent.ACTION_DOWN){
                level9?.startAnimation(DownAnim)
                val intent = Intent(this, MainActivitySetQues::class.java)
                intent.putExtra("level", "9")
                startActivity(intent)
                finish()
            }
            return@setOnTouchListener true
        }
        level10!!.setOnTouchListener { View, motionEvent ->
            if(motionEvent.action == MotionEvent.ACTION_UP){
                level10?.startAnimation(UpAnim)
            }else if(motionEvent.action == MotionEvent.ACTION_DOWN){
                level10?.startAnimation(DownAnim)
                val intent = Intent(this, MainActivitySetQues::class.java)
                intent.putExtra("level", "10")
                startActivity(intent)
                finish()
            }
            return@setOnTouchListener true
        }



    }
}