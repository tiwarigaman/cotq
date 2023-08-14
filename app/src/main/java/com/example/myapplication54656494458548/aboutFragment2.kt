package com.example.myapplication54656494458548

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class aboutFragment2 : Fragment() {
    private var mUsername : TextView? = null
    private var mName : TextView? = null
    private var mCollege : TextView? = null
    private var mTotalScore : TextView? = null
    private var mEditBtn : Button? = null
    private var currentUserID : String? = null
    private lateinit var userRef: DatabaseReference
    private var mImage : ImageView? = null
    private var mShareBtn : TextView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    private fun init(view: View) {

        val currentUser: String? = FirebaseAuth.getInstance().uid
        currentUserID = currentUser.toString()
        userRef = FirebaseDatabase.getInstance().reference


        mUsername = view.findViewById(R.id.about_username)
        mName = view.findViewById(R.id.about_name)
        mCollege = view.findViewById(R.id.about_Col_name)
        mTotalScore = view.findViewById(R.id.about_total_score)
        mImage = view.findViewById(R.id.btn_profile_click233)
        mEditBtn = view.findViewById(R.id.edit_profile)
        mShareBtn = view.findViewById(R.id.btn_share_profile)

        mShareBtn?.setOnClickListener {
            Toast.makeText(activity,"Your profile is shared.",Toast.LENGTH_SHORT).show()
        }
        mTotalScore?.text = "Total Points *100"


        mEditBtn?.setOnClickListener {
            /// this is for to intent in one fragment to another fragment.....
            val fr: FragmentTransaction = fragmentManager!!.beginTransaction()
            fr.replace(android.R.id.content, detailsFragment2())
            fr.commit()
        }




        userRef.child("Users").child(currentUserID!!).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.hasChild("userName")){
                    val userName = snapshot.child("userName").value.toString()
                    mUsername?.text = "$userName ~"
                }
                if (snapshot.hasChild("Name")){
                    val fullName = snapshot.child("Name").value.toString()
                    mName?.text=fullName
                }
                if (snapshot.hasChild("College Name")){
                    val colName = snapshot.child("College Name").value.toString()
                    mCollege?.text = colName
                }
                if (snapshot.exists()){
                    val image : String = snapshot.child("profile image").value.toString()
//                    Toast.makeText(activity, image,Toast.LENGTH_SHORT).show()
                    Glide.with(this@aboutFragment2).load(image).into(mImage!!)
//                    Picasso.with(context).load(image).into(mImage)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(activity,"Can't get your data", Toast.LENGTH_SHORT).show()
            }

        })



    }
}