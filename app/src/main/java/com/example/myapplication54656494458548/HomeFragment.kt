package com.example.myapplication54656494458548

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_community, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    private fun init(view: View?) {
//        firstFragment = view?.findViewById(R.id.Aman)
//        secondFragment = view?.findViewById(R.id.btn_frag_BTN)
//        secondFragment?.setOnClickListener{
//            var c=0
//            c++
//            Toast.makeText(activity,"$c Hey",Toast.LENGTH_SHORT).show()
//            firstFragment?.append("hey $c aman")
//        }

    }


}