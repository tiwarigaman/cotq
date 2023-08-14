package com.example.myapplication54656494458548

import android.content.Intent
import android.os.Build.VERSION_CODES.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics.Param.SCORE
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class resultactivity : AppCompatActivity() {
    private var userref : DatabaseReference? = null

    private var currentUserID : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultactivity)

        userref= FirebaseDatabase.getInstance().reference.child("Users").child(FirebaseAuth.getInstance().uid.toString())

        val tvName : TextView = findViewById(R.id.tv_name)
        val tvCol : TextView = findViewById(R.id.tv_collegeName)
        val tvScore : TextView = findViewById(R.id.tv_score)
        val btnFinish : Button = findViewById(R.id.btn_finish)
        val meet: TextView = findViewById(R.id.tv_congratulation)

//        tvName.text = intent.getStringExtra(constant1.USER_NAME)

        val currentUser = FirebaseAuth.getInstance().uid
        currentUserID = currentUser.toString()

        val totalQues = intent.getIntExtra(constant1.TOTAL_QUESTIONS,0)
        val corrects = intent.getIntExtra(constant1.CORRECT_ANSWER,0)
        val userRef : DatabaseReference = FirebaseDatabase.getInstance().reference

        userRef.child("Users").child(currentUserID!!).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.hasChild("Name")){
                    val fullName = snapshot.child("Name").value.toString()
                    tvName.text = fullName
                }
                if (snapshot.hasChild("College Name")){
                    val collegeName = snapshot.child("College Name").value.toString()
                    tvCol.append(collegeName)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

//        Toast.makeText(this, "$count is", Toast.LENGTH_SHORT).show()
        userref!!.child("total score").setValue("count").addOnSuccessListener {

        }
        if(corrects>5) {
            meet.text="Hey! Congratulations"
            tvScore.text = "Your Score is $corrects out of $totalQues"
        }else{
            meet.text="Better luck next time...."
            tvScore.text = "Your Score is $corrects out of $totalQues"
        }
        btnFinish.setOnClickListener {
            val intent = Intent(this,fragmentActvity::class.java)
            startActivity(intent)
            finish()
        }

    }
}