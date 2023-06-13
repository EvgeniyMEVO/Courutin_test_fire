package com.example.courutin_test_fire.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.cardview.widget.CardView
import com.example.courutin_test_fire.AuthManager
import com.example.courutin_test_fire.LoginActivity
import com.example.courutin_test_fire.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InfoProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InfoProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    // Получаем объект SharedPreferences
    val sharedPreferences = context?.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

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
        val view = inflater.inflate(R.layout.fragment_info_profile, container, false)
        val bt_back = view.findViewById<ImageView>(R.id.bt_back)
        val bt_info_acc = view.findViewById<CardView>(R.id.bt_info_acc)
        val bt_extra_info_acc = view.findViewById<CardView>(R.id.bt_extra_info_acc)

        bt_back.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SettingFragment())
                .addToBackStack(null)
                .commit()
        }
        bt_info_acc.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, UserPersonalInfo())
                .addToBackStack(null)
                .commit()
        }
        bt_extra_info_acc.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ExtraUserPersonalInfo())
                .addToBackStack(null)
                .commit()
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
         * @return A new instance of fragment InfoProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InfoProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}