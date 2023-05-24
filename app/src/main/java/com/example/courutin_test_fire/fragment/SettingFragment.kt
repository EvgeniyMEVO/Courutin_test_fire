package com.example.courutin_test_fire.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.courutin_test_fire.AuthManager
import com.example.courutin_test_fire.DatabaseManager
import com.example.courutin_test_fire.LoginActivity
import com.example.courutin_test_fire.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var currentFragment: Fragment? = null

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
        val currentFragment = sharedPreferences?.getInt("CURRENT_FRAGMENT", 0)
        if (currentFragment == 1) {
            // Открыть фрагмент информации о профиле
            val view = inflater.inflate(R.layout.fragment_setting, container, false)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, InfoProfileFragment())
                .addToBackStack(null)
                .commit()
            return view
        } else {
            // Inflate the layout for this fragment
            val view = inflater.inflate(R.layout.fragment_setting, container, false)
            val bt_exit = view.findViewById<CardView>(R.id.bt_exit)
            val bt_info_acc = view.findViewById<CardView>(R.id.bt_info_acc)
            val bt_send = view.findViewById<CardView>(R.id.bt_send)
            val tv_id = view.findViewById<TextView>(R.id.tv_id)
            var test_text = ""
            CoroutineScope(Dispatchers.IO).launch {
                test_text = "FireId: " +
                        AuthManager.getCurrentUserId() +
                        "\n" +
                        "Contract: " +
                        DatabaseManager.getUserContractAddress().getOrThrow().toString()
                withContext(Dispatchers.Main) {
                    tv_id.text = test_text
                }
            }
            bt_exit.setOnClickListener {
                AuthManager.signOut()
                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
            bt_info_acc.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, InfoProfileFragment())
                    .addToBackStack(null)
                    .commit()
            }
            bt_send.setOnClickListener{
                //val url = "http://10.0.2.2:5000/send_data"
                //val str = AuthManager.getCurrentUserId()
                //val json = "{\"UID\": \"$str\"}"
                //sendPost(url, json)
                CoroutineScope(Dispatchers.Main).launch {
                    val contract = DatabaseManager.getUserContractAddress().toString()
                    Toast.makeText(requireContext(),contract,Toast.LENGTH_SHORT).show()
                }
            }
            return view
        }
    }

    private fun sendPost(url: String, json: String) {
        val body = json.toRequestBody("application/json; charset=utf-8".toMediaType())
        val httpClient = OkHttpClient()

        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        httpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    throw IOException("Unexpected code $response")
                }

                println(response.body!!.string())
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}