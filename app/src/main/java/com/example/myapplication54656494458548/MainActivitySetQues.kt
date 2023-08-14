package com.example.myapplication54656494458548

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build.VERSION_CODES.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat


class MainActivitySetQues : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPos : Int =1
    private var mQuestionList : ArrayList<Question>? = null
    private var mSelectedOptionPos : Int = 0
    private var mUserName:String? =null
    private var mCorrectans:Int = 0

    private var progressBar : ProgressBar?=null
    private var tvProgress : TextView?= null
    private var tvQuestion : TextView?=null
    private var tvImage : ImageView?=null

    private var tvOptionOne: TextView?=null
    private var tvOptionTwo: TextView?=null
    private var tvOptionThree: TextView?=null
    private var tvOptionFour: TextView?=null
    private var btnSubmit: Button? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_set_ques)
        mUserName = intent.getStringExtra(constant1.USER_NAME)
//        mConcat = intent.getStringExtra(quizz_level)
        val level = intent.getStringExtra("level")

        progressBar = findViewById(R.id.progressbar)
        tvProgress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tvQuestion)
        tvImage = findViewById(R.id.tvImage)

        tvOptionOne = findViewById(R.id.option_one)
        tvOptionTwo = findViewById(R.id.option_two)
        tvOptionThree = findViewById(R.id.option_three)
        tvOptionFour = findViewById(R.id.option_four)
        btnSubmit = findViewById(R.id.btn_submit)

        when(level) {
            "1"->{
                mQuestionList = constant1.getQuestions()
            }
            "2"->{
                mQuestionList = constant2.getQuestions()
            }
            "3"->{
                mQuestionList = constant1.getQuestions()
            }
            "4"->{
                mQuestionList = constant1.getQuestions()
            }
            "5"->{
                mQuestionList = constant1.getQuestions()
            }
            "6"->{
                mQuestionList = constant1.getQuestions()
            }
            "7"->{
                mQuestionList = constant1.getQuestions()
            }
            "8"->{
                mQuestionList = constant1.getQuestions()
            }
            "9"->{
                mQuestionList = constant1.getQuestions()
            }
            "10"->{
                mQuestionList = constant1.getQuestions()
            }
        }

        Toast.makeText(this, "Level $level", Toast.LENGTH_SHORT).show()



        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionOne?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)



        btnSubmit?.setOnClickListener(this)

        setQuestion()
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion() {
        val question: Question = mQuestionList!![mCurrentPos - 1]
        defaultoptionview()

        if(mCurrentPos==mQuestionList!!.size){
            btnSubmit?.text = "FINISH"
        }else{
            btnSubmit?.text = "SUBMIT"
        }
//        if(btnSubmit?.text=="SUBMIT"){
//            btnSubmit?.text="GO TO NEXT QUESTION"
//        }
        progressBar?.progress = mCurrentPos
        tvProgress?.text = "$mCurrentPos/${progressBar?.max?.plus(1)}"

        tvQuestion?.text = question.question

//        tvImage?.setImageResource(question.image)
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour
    }
    private fun selectedoptionview(tv: TextView, selectedOpnum:Int){
        defaultoptionview()
        mSelectedOptionPos = selectedOpnum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,R.drawable.selected_question_border_bg
        )
    }

    private fun defaultoptionview(){
        val options = ArrayList<TextView>()
        tvOptionOne?.let {
            options.add(0,it)
        }
        tvOptionTwo?.let {
            options.add(1,it)
        }
        tvOptionThree?.let {
            options.add(2,it)
        }
        tvOptionFour?.let {
            options.add(3,it)
        }
        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,R.drawable.default_option_border
            )
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(view: View?) {
//        if(mSelectedOptionPos==0){
//            val a=0
//        }


        if(btnSubmit?.text=="GO TO NEXT QUESTION" || btnSubmit?.text=="FINISH"){
            if(btnSubmit?.text=="FINISH"){
                when (view?.id) {
                    R.id.option_one -> {
                        tvOptionOne?.let {
                            selectedoptionview(it, 1)
                        }
                    }
                    R.id.option_two -> {
                        tvOptionTwo?.let {
                            selectedoptionview(it, 2)
                        }
                    }
                    R.id.option_three -> {
                        tvOptionThree?.let {
                            selectedoptionview(it, 3)
                        }
                    }
                    R.id.option_four -> {
                        tvOptionFour?.let {
                            selectedoptionview(it, 4)
                        }
                    }
                    R.id.btn_submit ->{
                        val question = mQuestionList?.get(mCurrentPos - 1)
                        if (question!!.currectans != mSelectedOptionPos) {
                            answerView(mSelectedOptionPos, R.drawable.wrong_ans_view)

                        } else {
                            mCorrectans++
                            answerView(question.currectans, R.drawable.correct_ans_view)
                        }
                        if (mCurrentPos == mQuestionList!!.size) {
                            btnSubmit?.text = "FINISH"
                        } else {
                            if(mSelectedOptionPos==0){
                                btnSubmit?.text = "SUBMIT"
                            }else {
                                btnSubmit?.text = "GO TO NEXT QUESTION"
                            }
                        }

                        mSelectedOptionPos = 0
                    }
                }
            }
            when(view?.id) {
                R.id.btn_submit -> {
                    if (mSelectedOptionPos == 0) {
                        mCurrentPos++
                        when {
                            mCurrentPos <= mQuestionList!!.size -> {
                                setQuestion()
                            }
                            else -> {
                                val intent = Intent(this, resultactivity::class.java)
                                intent.putExtra(constant1.USER_NAME, mUserName)
                                intent.putExtra(constant1.CORRECT_ANSWER, mCorrectans)
                                intent.putExtra(constant1.TOTAL_QUESTIONS, mQuestionList?.size)
                                startActivity(intent)
                                finish()
                            }
                        }
                    } else {
                        val question = mQuestionList?.get(mCurrentPos - 1)
                        if (question!!.currectans != mSelectedOptionPos) {
                            answerView(mSelectedOptionPos, R.drawable.wrong_ans_view)

                        } else {
                            mCorrectans++
                            answerView(question.currectans, R.drawable.correct_ans_view)
                        }
                        if (mCurrentPos == mQuestionList!!.size) {
                            btnSubmit?.text = "FINISH"
                        } else {
                            if(mSelectedOptionPos==0){
                                btnSubmit?.text = "SUBMIT"
                            }else {
                                btnSubmit?.text = "GO TO NEXT QUESTION"
                            }
                        }

                        mSelectedOptionPos = 0
                    }
                }
            }
        }
        else {
            when (view?.id) {
                R.id.option_one -> {
                    tvOptionOne?.let {
                        selectedoptionview(it, 1)
                    }
                }
                R.id.option_two -> {
                    tvOptionTwo?.let {
                        selectedoptionview(it, 2)
                    }
                }
                R.id.option_three -> {
                    tvOptionThree?.let {
                        selectedoptionview(it, 3)
                    }
                }
                R.id.option_four -> {
                    tvOptionFour?.let {
                        selectedoptionview(it, 4)
                    }
                }
                R.id.btn_submit ->{
                    val question = mQuestionList?.get(mCurrentPos - 1)
                    if (question!!.currectans != mSelectedOptionPos) {
                        answerView(mSelectedOptionPos, R.drawable.wrong_ans_view)
                    } else {
                        mCorrectans++
                        answerView(question.currectans, R.drawable.correct_ans_view)
                    }
                    if (mCurrentPos == mQuestionList!!.size) {
                        btnSubmit?.text = "FINISH"
                    } else {
                        if(mSelectedOptionPos==0){
                            btnSubmit?.text = "SUBMIT"
                        }else {
                            btnSubmit?.text = "GO TO NEXT QUESTION"
                        }
                    }
                    mSelectedOptionPos = 0
                }
            }
        }
    }

    private fun answerView(answer: Int,drawableView: Int){
        when(answer){
            1->{
                tvOptionOne?.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2->{
                tvOptionTwo?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3->{
                tvOptionThree?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            4->{
                tvOptionFour?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }
}