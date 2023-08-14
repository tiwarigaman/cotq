package com.example.myapplication54656494458548

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.analytics.FirebaseAnalytics.Param.SCORE
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlin.system.exitProcess

class fragmentActvity : AppCompatActivity() {


    private var bottomNavigationView : BottomNavigationView? = null
    //Here define all fragment which are used...
    private var communityFragment = HomeFragment()
    private var levelsFragment = LevelsFragment()
    private var postFragment = postFragment()
    private var aboutFragment = aboutFragment2()
    private var detailsFragment = detailsFragment2()
//    private late init var count : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_actvity)



//        count = intent.extras.toString()
//        Toast.makeText(this,"$count is",Toast.LENGTH_SHORT).show()

        bottomNavigationView = findViewById(R.id.bottom_nav)


        val currentUser = FirebaseAuth.getInstance().uid
        val userRef : DatabaseReference = FirebaseDatabase.getInstance().reference.child("Users")
        userRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.hasChild(currentUser.toString())){
                    if(FirebaseAuth.getInstance().currentUser!=null) {
                        //this is for open default fragment activity....
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container_layout, communityFragment).commit()
                    }else{
                        finish()
                    }
                    bottomNavigationView?.setOnItemSelectedListener {

                        when(it.itemId){
                            //When person click on the community fragment...
                            R.id.navigation_Community->{
                                supportFragmentManager.beginTransaction().replace(R.id.container_layout,communityFragment).commit()
                                return@setOnItemSelectedListener true
                            }
                            R.id.navigation_Levels->{
                                supportFragmentManager.beginTransaction().replace(R.id.container_layout,levelsFragment).commit()

                                return@setOnItemSelectedListener true
                            }
                            R.id.navigation_personInfo->{
                                supportFragmentManager.beginTransaction().replace(R.id.container_layout,aboutFragment).commit()
                                return@setOnItemSelectedListener true
                            }
                            R.id.btn_post->{
                                supportFragmentManager.beginTransaction().replace(R.id.container_layout,postFragment).commit()
                                return@setOnItemSelectedListener true
                            }
                        }
                        return@setOnItemSelectedListener false
                    }
                }else{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container_layout, detailsFragment).commit()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}