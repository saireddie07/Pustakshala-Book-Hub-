package com.example.bookinfohub

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var logOutBtn:Button
    lateinit var sharedPreference: SharedPreferences
    lateinit var profileProgress:RelativeLayout
    lateinit var mainProfile:RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment



        val view= inflater.inflate(R.layout.fragment_profile, container, false)

        logOutBtn=view.findViewById(R.id.log_out_button)
        profileProgress=view.findViewById(R.id.profileProgress)
        mainProfile=view.findViewById(R.id.mainProfile)
        profileProgress.visibility=View.GONE
        sharedPreference= requireActivity().getSharedPreferences(getString(R.string.preference_log_in),Context.MODE_PRIVATE)
        logOutBtn.setOnClickListener {
            profileProgress.visibility=View.VISIBLE
            mainProfile.visibility=View.GONE
            sharedPreference.edit().putBoolean("isLogIn", false).apply()
            android.os.Handler().postDelayed({
            val intent=Intent(activity,LogInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            activity?.finish()},2000)
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}