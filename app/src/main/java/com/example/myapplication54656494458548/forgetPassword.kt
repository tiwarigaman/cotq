package com.example.myapplication54656494458548

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class forgetPassword : AppCompatActivity() {
    private var mBtnBack : ImageButton? = null
    private var mEnteredEmail : EditText? = null
    private var mBtnReset : Button? = null

    private var progresDi : ProgressDialog?= null

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        mAuth = Firebase.auth

        progresDi = ProgressDialog(this)

        mBtnBack  = findViewById(R.id.btn_back)
        mEnteredEmail = findViewById(R.id.btn_forget_email)
        mBtnReset = findViewById(R.id.btn_reset)


        mBtnBack?.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

        mBtnReset!!.setOnClickListener{
            perForAUth()
        }
    }

    private fun perForAUth() {
        val mEmailReset = mEnteredEmail?.text.toString()
        if(mEmailReset.isEmpty()){
            mEnteredEmail?.error="Please enter email."
        }else if (!isValidEmail(mEmailReset)){
            mEnteredEmail?.error = "Please Enter Valid Email Id"
        }else{
            progresDi?.setMessage("Please wait...\nwhile Sending link.....")
            progresDi?.setTitle("Resetting...")
            progresDi?.setCanceledOnTouchOutside(false)
            progresDi?.show()

            mAuth.sendPasswordResetEmail(mEmailReset).addOnCompleteListener {
                if(it.isSuccessful){
                    progresDi?.dismiss()
                    Toast.makeText(this,"Reset link is send to your email",Toast.LENGTH_SHORT).show()
                }else{
                    progresDi?.dismiss()
                    Toast.makeText(this,"${it.exception}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}