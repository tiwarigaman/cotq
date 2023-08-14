package com.example.myapplication54656494458548

import android.R.attr.password
import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import java.util.*
import kotlin.collections.set


class detailsFragment2 : Fragment() {

    private var mUsername : EditText? = null
    private var mName : EditText? = null
    private var mColllege : EditText? = null
    private var mEmail: TextView? = null
    private var mGender1 : RadioButton? = null
    private var mGender2 : RadioButton? = null
    private var mGender : String? = null
    private var mImage : ImageView? = null
    private var mPhone : EditText? = null
    private var SaveBtn : ImageButton? = null
    private var mGenText : TextView ? = null
    private var dob : TextView? = null
    private var mBack : ImageButton? = null
    private lateinit var userRef: DatabaseReference

    private lateinit var UserImage : StorageReference

    //these are used for upload pic..
    private lateinit var storageReference : StorageReference
    private lateinit var storage : FirebaseStorage

    // this is for progress bar..
    private var progresDi : ProgressDialog?= null

    private var imageUri : Uri?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storage= FirebaseStorage.getInstance()
        storageReference= storage.reference.child("profile images")
        progresDi = ProgressDialog(activity)
        init(view)

    }

    @Deprecated("Deprecated in Java", ReplaceWith(
        "super.onActivityResult(requestCode, resultCode, data)",
        "androidx.fragment.app.Fragment"
    )
    )
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==1 && resultCode==RESULT_OK && data!=null && data.data !=null){
            imageUri = data.data
            //this is for temp save file
//            mImage?.setImageURI(imageUri)
            uploadPic()
        }
    }

    private fun uploadPic() {

        val riversRef = storageReference.child(FirebaseAuth.getInstance().uid.toString())
        progresDi?.setTitle("Uploading...")
        progresDi?.setCanceledOnTouchOutside(false)
        progresDi?.show()

        riversRef.putFile(imageUri!!).addOnSuccessListener{
                progresDi?.dismiss()
            riversRef.downloadUrl.addOnSuccessListener {

                userRef.child("profile image").setValue(it.toString()).addOnSuccessListener{
                    val fr: FragmentTransaction = fragmentManager!!.beginTransaction()
                    fr.replace(android.R.id.content, detailsFragment2())
                    fr.commit()
                    Toast.makeText(activity,"Profile pic Upload Successfully.",Toast.LENGTH_SHORT).show()
                }
            }
        }.addOnProgressListener {
            val progressPercent : Double = (100.00 * it.bytesTransferred / it.totalByteCount)
            progresDi?.setMessage(" ${progressPercent.toInt()}% Done.")
        }
    }


    private fun init(view: View) {
        var currentUser: String? = null

        if (FirebaseAuth.getInstance().currentUser!=null) {
            currentUser= FirebaseAuth.getInstance().currentUser?.email.toString()
        }

        mUsername = view.findViewById(R.id.btn_set_username)
        userRef = FirebaseDatabase.getInstance().reference.child("Users").child(FirebaseAuth.getInstance().uid.toString())
        UserImage = FirebaseStorage.getInstance().reference.child("Profile Picture")
        userRef.keepSynced(true)



//        userRef.addValueEventListener(object : ValueEventListen`er{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()){
//                    val image : String = snapshot.child("profile image").value.toString()
////                    Toast.makeText(activity, image,Toast.LENGTH_SHORT).show()
//                    Picasso.with(this@detailsFragment2.context).load(image).into(mImage)
//                }
//            }
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        })


        mName = view.findViewById(R.id.btn_set_name)
        dob = view.findViewById(R.id.btn_dob1)
        mPhone = view.findViewById(R.id.btn_phone1)
        mColllege = view.findViewById(R.id.btn_college_name1)
        mEmail = view.findViewById(R.id.shown_email1)
        mImage = view.findViewById(R.id.btn_profile_clicking1)
        SaveBtn = view.findViewById(R.id.btn_save1)
        mGender1 = view.findViewById(R.id.btn_male1)
        mGender2 = view.findViewById(R.id.btn_female1)
        mBack = view.findViewById(R.id.btn_back)
        mGenText = view.findViewById(R.id.gender_text)

        var logout : TextView? = null
        logout = view.findViewById(R.id.logout_details)
        logout?.setOnClickListener {
            Firebase.auth.signOut()
            val intent= Intent(activity,LoginActivity::class.java)
            Toast.makeText(activity, "Logout Successfully.", Toast.LENGTH_SHORT).show()
            startActivity(intent)
            activity?.finish()
        }

        mBack?.setOnClickListener {
            val intent = Intent(activity,fragmentActvity::class.java)
            startActivity(intent)
            activity!!.finish()
//            fragmentManager!!.beginTransaction().replace(android.R.id.content, about_fragment()).commit()
//            val fr: FragmentTransaction = fragmentManager!!.beginTransaction()
//            fr.replace(android.R.id.content, about_fragment())
//            fr.commit()
        }

        dob?.setOnClickListener{
            myDatepick(view)
        }

        mImage?.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1)
        }



        mGender1?.setOnClickListener {
            mGender = ""
            mGender = "Male"
        }
        mGender2?.setOnClickListener {
            mGender = ""
            mGender = "Female"
        }


        mEmail?.append("$currentUser")


        SaveBtn?.setOnClickListener{
            saveAccountInfo()
        }

        userRef.addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.hasChild("userName")){
                    val userName= snapshot.child("userName").value
//                    mUsername?.append(userName.toString())
                    mUsername?.setText(userName.toString())
                }
                if (snapshot.hasChild("Name")){
                    val fullName = snapshot.child("Name").value
//                    mName?.append(fullName.toString())
                    mName?.setText(fullName.toString())
                }
                if (snapshot.hasChild("College Name")){
                    val colName = snapshot.child("College Name").value.toString()
//                    mColllege?.append(colName)
                    mColllege?.setText(colName)
                }
                if(snapshot.hasChild("Date Of Birth")){
                    val mdob = snapshot.child("Date Of Birth").value.toString()
//                    dob?.append(mdob)
                    dob?.setText(mdob)
                }
                if(snapshot.hasChild("Mobile Number")){
                    val mMobile = snapshot.child("Mobile Number").value.toString()
//                    mPhone?.append(mMobile)
                    mPhone?.setText(mMobile)
                }
                if(snapshot.hasChild("profile image")){
                    val mmage = snapshot.child("profile image").value.toString()
                    Glide.with(this@detailsFragment2).load(mmage).into(mImage!!)
                }
                if(snapshot.hasChild("Gender")){
                    val mGen = snapshot.child("Gender").value.toString()
                    mGenText?.text = "$mGen is Selected."
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(activity,"Can't get your data",Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun myDatepick(view: View) {
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(
            activity!!,
            { view, year, month, day ->
                val selectdate = "$day/${month+1}/$year"
                dob?.text = selectdate
//                dob?.text?.
//                append(selectdate)
            },
            year,
            month,
            day
        ).show()
    }

    private fun saveAccountInfo() {
        val userName = mUsername?.text.toString()
        val name = mName?.text.toString()
        val mobileNo = mPhone?.text.toString()
        val college = mColllege?.text.toString()
        val mDob = dob?.text.toString()
        val mgen = mGender


        if(TextUtils.isEmpty(userName)){
            mUsername?.error = "Please! Create a username."
        }else if (TextUtils.isEmpty(name)){
            mName?.error ="Please! Enter name."
        }else if(TextUtils.isEmpty(mobileNo)){
            mPhone?.error = "Phone number is required."
        }else if (TextUtils.isEmpty(college)){
            mColllege?.error = "Please! enter college name."
        }else if(mobileNo.length!=10){
            mPhone?.error = "Please! Enter a valid phone number."
        }else if (TextUtils.isEmpty(mGender)){
            mGender2?.error = "Please! Pick one."
        }else if (TextUtils.isEmpty(mDob)){
            dob?.error = "Please! enter dob."
        }else{
            progresDi?.setMessage("Please wait...\nwhile Saving your information.....")
            progresDi?.setTitle("Saving...")
            progresDi?.setCanceledOnTouchOutside(false)
            progresDi?.show()


            val userMap : HashMap<String, String> = HashMap()
            userMap["userName"] = userName
            userMap["Name"] = name
            userMap["Mobile Number"] = mobileNo
            userMap["College Name"] = college
            userMap["Date Of Birth"] = mDob
            userMap["Gender"] = mgen.toString()


            userRef.updateChildren(userMap as Map<String, Any>).addOnCompleteListener{

                if(it.isSuccessful){
                    progresDi?.dismiss()
                    Toast.makeText(activity,"Your Info Save successfully.",Toast.LENGTH_SHORT).show()
                    val intent = Intent(activity,fragmentActvity::class.java)
                    startActivity(intent)
                    activity?.finish()
                }else{
                    progresDi?.dismiss()
                    Toast.makeText(activity,"${it.exception}",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }


}