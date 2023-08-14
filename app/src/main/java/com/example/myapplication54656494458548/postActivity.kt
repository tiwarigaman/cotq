package com.example.myapplication54656494458548

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

class postActivity : AppCompatActivity() {

    private var mName : TextView? = null
    private var mCollege : TextView? = null
    private var currentUserID : String? = null
    private var mPostImage : ImageView? = null
    private lateinit var userRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        userRef = FirebaseDatabase.getInstance().reference

        mName = findViewById(R.id.btn_Name_post)
        mCollege = findViewById(R.id.btn_college_post)
        mPostImage = findViewById(R.id.btn_profile_post)

        userRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    val image : String = snapshot.child("profile image").value.toString()
//                    Toast.makeText(activity, image,Toast.LENGTH_SHORT).show()
                    Picasso.with(this@postActivity).load(image).placeholder(R.drawable.profile).into(mPostImage)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


        val currentUser = FirebaseAuth.getInstance().uid
        currentUserID = currentUser.toString()

        val userRef :DatabaseReference = FirebaseDatabase.getInstance().reference

        userRef.child("Users").child(currentUserID!!).addValueEventListener(object :
            ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.hasChild("Name")){
                    val fullName = snapshot.child("Name").value.toString()
                    mName?.text= fullName
                }
                if (snapshot.hasChild("College Name")){
                    val colName = snapshot.child("College Name").value.toString()
                    mCollege?.text = colName
                }
                if (snapshot.exists()){
                    val image : String = snapshot.child("profile image").value.toString()
                    Glide.with(this@postActivity).load(image).into(mPostImage!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        // Call the user for the post..
        postByUser()
    }

    private fun postByUser() {


    }
}