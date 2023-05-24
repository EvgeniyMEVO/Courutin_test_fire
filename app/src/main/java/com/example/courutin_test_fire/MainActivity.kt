package com.example.courutin_test_fire


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.courutin_test_fire.databinding.ActivityMainBinding
import com.example.courutin_test_fire.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val homeFragment = HomeFragment()
    private val messageFragment = MessageFragment()
    private val questionsFragment = QuestionsFragment()
    private val settingFragment = SettingFragment()

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val currentUser = AuthManager.getCurrentUser()
        if (currentUser == null)
            noSuchCurrentUser()
        else {
            Toast.makeText(this, AuthManager.getCurrentUserId(), Toast.LENGTH_SHORT).show()
            val userId = AuthManager.getCurrentUserId().toString() // get authenticated user id
            DatabaseManager.setUserId(userId)
            //else
            //binding.textView.text = "Hello ${currentUser.email}"
            //binding!!.btExit.setOnClickListener { exitInAccount() }

            // Отображаем HomeFragment при запуске приложения
            if (savedInstanceState == null) {
                replaceFragment(homeFragment)
            }

            bottomNavigationView = findViewById(R.id.bottom_navigation)

            bottomNavigationView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.navigation_home -> replaceFragment(homeFragment)
                    R.id.navigation_questions -> replaceFragment(questionsFragment)
                    R.id.navigation_message -> replaceFragment(messageFragment)
                    R.id.navigation_setting -> replaceFragment(settingFragment)
                }
                true
            }
        }
    }

    private fun replaceFragment(fragment: Fragment){
        if (fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }

    private fun noSuchCurrentUser() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun exitInAccount() {
        AuthManager.signOut()
        noSuchCurrentUser()
    }
}
