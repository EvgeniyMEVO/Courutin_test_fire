package com.example.courutin_test_fire.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.courutin_test_fire.DatabaseManager
import com.example.courutin_test_fire.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ExtraUserPersonalInfo.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExtraUserPersonalInfo : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        val view = inflater.inflate(R.layout.fragment_extra_user_personal_info, container, false)
        val bt_back = view.findViewById<ImageView>(R.id.bt_back)
        val cityEditText = view.findViewById<TextInputEditText>(R.id.city_edit_text)
        val streetEditText = view.findViewById<TextInputEditText>(R.id.street_edit_text)
        val houseEditText = view.findViewById<TextInputEditText>(R.id.house_edit_text)
        val indexEditText = view.findViewById<TextInputEditText>(R.id.index_edit_text)
        val save_button = view.findViewById<MaterialButton>(R.id.save_button)


        var city: String? = null
        var street: String? = null
        var house: String? = null
        var index: String? = null

        bt_back.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, InfoProfileFragment())
                .addToBackStack(null)
                .commit()
        }

        CoroutineScope(Dispatchers.IO).launch {
            city = DatabaseManager.getAnyInfo("city").getOrThrow()
            street = DatabaseManager.getAnyInfo("street").getOrThrow()
            house = DatabaseManager.getAnyInfo("house").getOrThrow()
            index = DatabaseManager.getAnyInfo("index").getOrThrow()
            withContext(Dispatchers.Main) {
                city?.let { cityEditText?.setText(it) }
                street?.let { streetEditText?.setText(it) }
                house?.let { houseEditText?.setText(it) }
                index?.let { indexEditText?.setText(it) }
            }
        }

        save_button.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                cityEditText?.text?.toString()?.let {
                    if (it != city) {
                        DatabaseManager.saveAnyInfo("city", it)
                    }
                }
                streetEditText?.text?.toString()?.let {
                    if (it != street) {
                        DatabaseManager.saveAnyInfo("street", it)
                    }
                }
                houseEditText?.text?.toString()?.let {
                    if (it != house) {
                        DatabaseManager.saveAnyInfo("house", it)
                    }
                }
                indexEditText?.text?.toString()?.let {
                    if (it != index) {
                        DatabaseManager.saveAnyInfo("index", it)
                    }
                }
            }
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
         * @return A new instance of fragment ExtraUserPersonalInfo.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ExtraUserPersonalInfo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}