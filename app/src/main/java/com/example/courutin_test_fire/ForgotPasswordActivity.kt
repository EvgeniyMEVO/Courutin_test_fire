package com.example.courutin_test_fire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.example.courutin_test_fire.databinding.ActivityForgotPasswordBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    private val TAG = "ForgotPasswordActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btSendResetPassword!!.setOnClickListener { sendPasswordEmail() }
    }

    private fun sendPasswordEmail() {
        var email = binding.edForgotEmail?.text.toString().trim() { it <= ' ' }
        if (TextUtils.isEmpty(email)) {
            binding.edForgotEmail.error = getString(R.string.er_enter_email)
        } else {
            binding.progresForgot.visibility = View.VISIBLE
            GlobalScope.launch {
                val result = AuthManager.forgotPassword(email)
                runOnUiThread { binding.progresForgot.visibility = View.GONE }
                withContext(Dispatchers.Main) {
                    if (result.isSuccess) {
                        Toast.makeText(baseContext, getString(R.string.forgot_password_successful), Toast.LENGTH_SHORT).show()
                        updateUI()
                    } else{
                        Toast.makeText(baseContext, getString(R.string.er_forgot_password), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
    private fun updateUI() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}