package com.example.courutin_test_fire.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.toColor
import com.example.courutin_test_fire.AuthManager
import com.example.courutin_test_fire.BlockchainManager
import com.example.courutin_test_fire.DatabaseManager
import com.example.courutin_test_fire.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var dateEditText: TextInputEditText
private lateinit var date: String

/**
 * A simple [Fragment] subclass.
 * Use the [SendInfoJKHBlockchain.newInstance] factory method to
 * create an instance of this fragment.
 */
class SendInfoJKHBlockchain : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_send_info_j_k_h_blockchain, container, false)

        val amountEditText = view.findViewById<TextInputEditText>(R.id.amount_edit_text)
        dateEditText = view.findViewById(R.id.date_edit_text)


        val bt_back = view.findViewById<ImageView>(R.id.bt_back)
        val bt_send = view.findViewById<MaterialButton>(R.id.send_button)


        bt_send.setOnClickListener {
            val amount = amountEditText.text.toString()
            val formattedDate = date.replace(".", "")
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    BlockchainManager.getInstance().setInitialBill(
                        BigInteger.valueOf(formattedDate.toLong()),
                        BigInteger.valueOf(amount.toLong()))
                    withContext(Dispatchers.Main){
                        Toast.makeText(requireContext(),"Sending is successful",Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Log.e("SendAmountError", "Error getting greeting", e)
                }
            }
        }
        dateEditText.setOnClickListener {
            val now = Calendar.getInstance()
            DatePickerDialog(requireContext(), dateSetListener,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        bt_back.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .addToBackStack(null)
                .commit()
        }

        return view
    }

    private val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        date = String.format(Locale.getDefault(), "%02d.%02d.%d", dayOfMonth, month+1, year)
        dateEditText.setText(date)
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SendInfoJKHBlockchain.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SendInfoJKHBlockchain().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}