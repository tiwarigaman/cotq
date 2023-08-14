package com.example.myapplication54656494458548

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class postFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
            /// this is for to intent in one fragment to another fragment.....
//            val fr: FragmentTransaction = fragmentManager!!.beginTransaction()
//            fr.replace(android.R.id.content, DetailsFragment())
//            fr.commit()
        val intent = Intent(activity,postActivity::class.java)
        startActivity(intent)
//        activity?.finish()

    }
}