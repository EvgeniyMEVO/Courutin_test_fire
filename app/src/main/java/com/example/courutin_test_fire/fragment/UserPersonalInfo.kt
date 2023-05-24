package com.example.courutin_test_fire.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.example.courutin_test_fire.AuthManager
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
 * Use the [UserPersonalInfo.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserPersonalInfo : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_user_personal_info, container, false)
        val emailEditText = view.findViewById<TextInputEditText>(R.id.email_edit_text)
        val nameEditText = view.findViewById<TextInputEditText>(R.id.name_edit_text)
        val surnameEditText = view.findViewById<TextInputEditText>(R.id.surname_edit_text)
        val phoneEditText = view.findViewById<TextInputEditText>(R.id.phone_edit_text)
        val bt_back = view.findViewById<ImageView>(R.id.bt_back)
        val save_button = view.findViewById<MaterialButton>(R.id.save_button)
        bt_back.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, InfoProfileFragment())
                .addToBackStack(null)
                .commit()
        }
        // загрузка информации из БД
        CoroutineScope(Dispatchers.IO).launch {
            val user_email = AuthManager.getCurrentUserEmail().toString()
            val name = DatabaseManager.getAnyInfo("name").getOrThrow()
            val firstName = DatabaseManager.getAnyInfo("firstname").getOrThrow()
            val phone = DatabaseManager.getAnyInfo("phone").getOrThrow()


            withContext(Dispatchers.Main) {
                emailEditText?.setText(user_email)
                name?.let { nameEditText?.setText(it) }
                firstName?.let { surnameEditText?.setText(it) }
                phone?.let { phoneEditText?.setText(it) }

                // проверяем кнопку сохранить
                save_button.setOnClickListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        val newEmail = emailEditText?.text.toString()
                        if (newEmail != user_email) { // проверяем, изменился ли email
                            // потом необходимо подвердить на почте, можно добавить какой-то флаг в БД позже для информирования
                            AuthManager?.updateEmail(newEmail)
                        }

                        val newName = nameEditText?.text.toString()
                        if (newName != name) { // проверяем, изменилось ли имя
                            DatabaseManager.saveAnyInfo("name", newName)
                        }

                        val newFirstName = surnameEditText?.text.toString()
                        if (newFirstName != firstName) { // проверяем, изменилась ли фамилия
                            DatabaseManager.saveAnyInfo("firstname", newFirstName)
                        }

                        val newPhone = phoneEditText?.text.toString()
                        if (newPhone != phone) { // проверяем, изменился ли телефон
                            DatabaseManager.saveAnyInfo("phone", newPhone)
                        }
                    }
                }

            }

        }

        // Inflate the layout for this fragment
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UserPersonalInfo.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserPersonalInfo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}