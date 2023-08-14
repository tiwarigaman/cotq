package com.example.myapplication54656494458548

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class SignupActivity : AppCompatActivity() {
    private var mCheck = false
    private var count = 0

    private var mInputemail : EditText? = null
    private var mInputpass : EditText? = null
    private var mConfirmpass : EditText? = null
    private var mBtnreg : Button? = null
    private var progresDi : ProgressDialog?= null

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        mInputemail = findViewById(R.id.btn_email)
        mInputpass = findViewById(R.id.btn_password)
        mConfirmpass = findViewById(R.id.btn_passwordconfirm)
        mBtnreg = findViewById(R.id.btn_signup)
        progresDi = ProgressDialog(this)



        mAuth = Firebase.auth


        mInputemail?.background = ContextCompat.getDrawable(
            this,R.drawable.default_for_sign_up
        )
        mInputpass?.background = ContextCompat.getDrawable(
            this,R.drawable.default_for_sign_up
        )
        mConfirmpass?.background = ContextCompat.getDrawable(
            this,R.drawable.default_for_sign_up
        )

        val mSignIn : Button = findViewById(R.id.btn_signin)
        mSignIn.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        mBtnreg?.setOnClickListener {
            PerforAuth()
        }
    }
    private fun PerforAuth(){
        val mEmail = mInputemail?.text.toString()
        val mPass = mInputpass?.text.toString()
        val mConPass = mConfirmpass?.text.toString()
        if(!isValidEmail(mEmail)){
            mInputemail?.error = "Please enter valid email."
        }
        else if(mPass.isEmpty() || mPass< 6.toString()){
            mInputpass?.error = "Check a password between 6 to 20 characters which contain at" +
                    " least one numeric digit, one uppercase and one lowercase letter.\n" +
                    "or Password field is empty."

        }else if (mPass != mConPass){
            mConfirmpass?.error = "Password does not matched."
        }else{
            if (mCheck){
            progresDi?.setMessage("Please wait while registration....")
            progresDi?.setTitle("Registration")
            progresDi?.setCanceledOnTouchOutside(false)
            progresDi?.show()


            mAuth.createUserWithEmailAndPassword(mEmail,mPass).addOnCompleteListener {
                if (it.isSuccessful) {
                    progresDi?.dismiss()
                    sendUserToVerification()
                    Toast.makeText(this, "Registration Successful.", Toast.LENGTH_SHORT).show()
                } else {
                    progresDi?.dismiss()
                    Toast.makeText(this, "${it.exception}", Toast.LENGTH_SHORT).show()
                }
            }
            }else{
                Toast.makeText(this, "Please Check Term and condition...then click to SIGNUP.", Toast.LENGTH_SHORT).show()
            }


        }
    }

    private fun sendUserToVerification() {
        FirebaseAuth.getInstance().currentUser?.sendEmailVerification()?.addOnSuccessListener{
            Toast.makeText(this,"Email verification sent Successfully. Please verify first.",Toast.LENGTH_LONG).show()
        }?.addOnFailureListener {
            Toast.makeText(this,"Email verification not sent.Please provide valid Email.",Toast.LENGTH_SHORT).show()
        }
    }


    fun onCheck(view: View){
        count++
        mCheck = count>0 && count%2!=0
    }
    private fun isValidEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}
