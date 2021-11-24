package com.example.tabwithviewpager.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tabwithviewpager.R


/**
 * A simple [Fragment] subclass.
 * Use the [QAFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QAFragment : Fragment() {

    companion object{
        @JvmStatic
        fun newInstance():ProjectFragment{
            return ProjectFragment()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_q_a, container, false)
    }

}