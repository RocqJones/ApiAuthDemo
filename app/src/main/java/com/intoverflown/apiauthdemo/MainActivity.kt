package com.intoverflown.apiauthdemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.intoverflown.apiauthdemo.data.LoginResponse
import com.intoverflown.apiauthdemo.data.SharedPreferenceManager
import com.intoverflown.apiauthdemo.databinding.ActivityMainBinding
import com.intoverflown.apiauthdemo.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        if (SharedPreferenceManager.getInstance(this).isLoggedIn()) {
            val loginResponse: LoginResponse = SharedPreferenceManager.getInstance(this).getUser()

            binding!!.userName.text = loginResponse.Username
        } else {
            val i = Intent(this, LoginActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)
            finish()
        }

        binding!!.logOutBtn.setOnClickListener {
            SharedPreferenceManager.getInstance(applicationContext).logout()
        }
    }
}