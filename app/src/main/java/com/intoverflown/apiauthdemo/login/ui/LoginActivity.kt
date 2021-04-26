package com.intoverflown.apiauthdemo.login.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.intoverflown.apiauthdemo.R
import com.intoverflown.apiauthdemo.login.models.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
    }
}