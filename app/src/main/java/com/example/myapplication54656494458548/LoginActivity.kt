package com.example.myapplication54656494458548

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    private var mEnteremail : EditText? = null
    private var mEnterpass : EditText? = null
    private var mBtnreg : Button? = null
    private var progresDi : ProgressDialog?= null


    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        mEnteremail = findViewById(R.id.btn_email2)
        mEnterpass = findViewById(R.id.password)
        val login : Button = findViewById(R.id.btn_Login)
        mBtnreg = findViewById(R.id.btnsignup)
        val forgets : Button = findViewById(R.id.forget)
        progresDi = ProgressDialog(this)

        mEnteremail?.background = ContextCompat.getDrawable(
            this,R.drawable.default_for_sign_up
        )
        mEnterpass?.background = ContextCompat.getDrawable(
            this,R.drawable.default_for_sign_up
        )
        mAuth = Firebase.auth

//        mUser = mAuth.currentUser!!
        forgets.setOnClickListener{
            startActivity(Intent(this,forgetPassword::class.java))
        }


        login.setOnClickListener {
//            startActivity(Intent(this,Activity_Userdetails::class.java))
            PerForAuth()
        }
        mBtnreg?.setOnClickListener {
            startActivity(Intent(this,SignupActivity::class.java))
        }
    }
    private fun PerForAuth(){

        val mEmail = mEnteremail?.text.toString()
        val mPass = mEnterpass?.text.toString()


        if(!isValidEmail(mEmail)){
            mEnteremail?.error = "Please enter valid email."
        }
        else if(mPass.isEmpty() || mPass< 6.toString()){
            mEnterpass?.error = "Check a password between 6 to 20 characters which contain at" +
                    " least one numeric digit, one uppercase and one lowercase letter.\n" +
                    "or Password field is empty."
        }else{
            progresDi?.setMessage("Please wait...\nwhile Login...")
            progresDi?.setTitle("Login")
            progresDi?.setCanceledOnTouchOutside(false)
            progresDi?.show()

            mAuth.signInWithEmailAndPassword(mEmail,mPass).addOnCompleteListener(){
                if(it.isSuccessful && FirebaseAuth.getInstance().currentUser?.isEmailVerified != false){
                    progresDi?.dismiss()
                    sendUserToNextActivity()
                    Toast.makeText(this,"Login Successful.", Toast.LENGTH_SHORT).show()
                    val user = mAuth.currentUser
                    updateUI(user)
                }else{
                    progresDi?.dismiss()
                    Toast.makeText(this,"${it.exception}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        if(currentUser!=null && FirebaseAuth.getInstance().currentUser?.isEmailVerified != false) {
            updateUI(currentUser)
        }
    }
    private fun updateUI(firebaseUser: FirebaseUser?){
         if(firebaseUser!=null){
            val intent= Intent(this,fragmentActvity::class.java)
            startActivity(intent)
            finish()

        }else{
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun sendUserToNextActivity(){
            val intent = Intent(this, fragmentActvity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
    }
    private fun isValidEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}

