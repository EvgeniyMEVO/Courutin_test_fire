package com.example.courutin_test_fire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.courutin_test_fire.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.*
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import org.web3j.tx.ReadonlyTransactionManager
import org.web3j.tx.gas.DefaultGasProvider
import org.web3j.tx.gas.StaticGasProvider
import java.math.BigInteger

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val TAG = "LoginActivity"
    //global variables
    private var email: String? = null
    private var password: String? = null
    private var flag_ender_data = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btTest!!.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    BlockchainManager.getInstance().setData(BigInteger.valueOf(1), BigInteger.valueOf(2), BigInteger.valueOf(3))
                } catch (e: Exception) {
                    Log.e("MainActivity", "Error getting greeting", e)
                }
            }
        }

        binding.idChange!!.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val contracts = BlockchainManager.getInstance().getLastUserStorageContract()
                    withContext(Dispatchers.Main) {
                        binding.tvTest.text = contracts
                    }
                } catch (e: Exception) {
                    Log.e("MainActivity", "Error getting greeting", e)
                }
            }
        }

        binding.button!!.setOnClickListener { signInAccount() }


    }

    private fun signInAccount(){
        email = binding.edLog?.text.toString().trim() { it <= ' ' }
        password = binding.edPas?.text.toString()
        if (TextUtils.isEmpty(email)){
            binding.edLog.error = getString(R.string.er_enter_email)
            flag_ender_data = false}
        if (TextUtils.isEmpty(password)){
            binding.edPas.error = getString(R.string.er_enter_password)
            flag_ender_data = false}
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) )
            flag_ender_data = true

        if (flag_ender_data==true){
            binding.progresLog.visibility = View.VISIBLE
            GlobalScope.launch {
                val result = AuthManager.signIn(email!!, password!!)
                runOnUiThread { binding.progresLog.visibility = View.GONE }
                withContext(Dispatchers.Main) {
                    if (result.isSuccess) {
                        Toast.makeText(baseContext, "Login successful", Toast.LENGTH_SHORT).show()
                        updateUI()
                    } else {
                        when ( val exception = result.exceptionOrNull()){
                            is FirebaseAuthInvalidUserException -> {
                                // User not found
                                Toast.makeText(
                                    baseContext,
                                    getString(R.string.er_login_api_user_not_found),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            is FirebaseAuthInvalidCredentialsException -> {
                                // Invalid credentials
                                if (exception.errorCode == "ERROR_WRONG_PASSWORD") {
                                    Toast.makeText(
                                        baseContext,
                                        getString(R.string.er_login_api_wrong_password),
                                        Toast.LENGTH_LONG
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        baseContext,
                                        getString(R.string.er_login_api_invalid_credentials),
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                            is FirebaseAuthWeakPasswordException -> {
                                // Weak password
                                Toast.makeText(
                                    baseContext,
                                    getString(R.string.er_register_api_password_invalid),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            // Other errors
                            else -> {
                                Toast.makeText(
                                    baseContext,
                                    "Login failed: ${exception?.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                        }
                    }
                }
            }
        }
    }
    fun onClickRegister(view: View){
        startActivity(Intent(this, RegisterActivity::class.java))
        finish()
    }
    private fun updateUI(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun onClickForgotPassword(view: View){
        startActivity(Intent(this, ForgotPasswordActivity::class.java))
    }
}