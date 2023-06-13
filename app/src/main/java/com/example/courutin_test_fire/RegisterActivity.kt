package com.example.courutin_test_fire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.courutin_test_fire.databinding.ActivityMainBinding
import com.example.courutin_test_fire.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.*
import java.math.BigInteger

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private val TAG = "RegisterActivity"

    //global variables
    private var firstName: String? = null
    private var name: String? = null
    private var email: String? = null
    private var password: String? = null
    private var flag_ender_data = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button!!.setOnClickListener { createNewAccount() }
    }

    private fun createNewAccount(){
        firstName = binding.edFirstname?.text.toString().trim() { it <= ' ' }
        name = binding.edName?.text.toString().trim() { it <= ' ' }
        email = binding.edLogin?.text.toString().trim() { it <= ' ' }
        password = binding.edPassword?.text.toString()

        // проверяем заполнение всех полей
        if (TextUtils.isEmpty(firstName)){
            binding.edFirstname.error = getString(R.string.er_enter_firstname)
            flag_ender_data = false}
        if (TextUtils.isEmpty(name)){
            binding.edName.error =  getString(R.string.er_enter_lastname)
            flag_ender_data = false}
        if (TextUtils.isEmpty(email)){
            binding.edLogin.error =  getString(R.string.er_enter_email)
            flag_ender_data = false}
        if (TextUtils.isEmpty(password)){
            binding.edPassword.error =  getString(R.string.er_enter_password)
            flag_ender_data = false}
        if (!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) )
            flag_ender_data = true

        // в
        if (flag_ender_data==true){
            binding.progresReg.visibility = View.VISIBLE
            GlobalScope.launch {
                val result = AuthManager.signUp(email!!, password!!)
                runOnUiThread { binding.progresReg.visibility = View.GONE }
                withContext(Dispatchers.Main) {
                    if (result.isSuccess) {
                        Toast.makeText(baseContext, getString(R.string.register_successful), Toast.LENGTH_SHORT).show()
                        createUserContract(firstName!!, name!!)
                        updateUI()
                    } else {
                        // обработка ошибок
                        // ("ERROR_INVALID_EMAIL", "The email address is badly formatted."));
                        // ("ERROR_EMAIL_ALREADY_IN_USE", "The email address is already in use by another account."));
                        when (val exception = result.exceptionOrNull()){
                            is FirebaseAuthUserCollisionException -> {
                                Toast.makeText(
                                    baseContext,
                                    getString(R.string.er_register_api_email_already_use),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            is FirebaseAuthWeakPasswordException -> {
                                Toast.makeText(
                                    baseContext,
                                    getString(R.string.er_register_api_password_invalid),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            // остальные ошибки
                            else -> {
                                Toast.makeText(
                                    baseContext,
                                    "Register failed: ${exception?.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun createUserContract(firstName: String, name: String){
        // создание контракта
        // привязка контракта к аккаунту
        CoroutineScope(Dispatchers.IO).launch {
            DatabaseManager.saveAnyInfo("firstname", firstName)
            DatabaseManager.saveAnyInfo("name", name)
            BlockchainManager.getInstance().setFabricaContractAddress()
            BlockchainManager.getInstance().createUserStorage()
            val adress = BlockchainManager.getInstance().getLastUserStorageContract()
            DatabaseManager.saveUserContractAddress(adress!!)
        }
    }

    fun onClickLogin(view: View){
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun updateUI(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}