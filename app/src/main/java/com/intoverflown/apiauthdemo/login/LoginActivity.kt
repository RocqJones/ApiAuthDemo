package com.intoverflown.apiauthdemo.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.intoverflown.apiauthdemo.MainActivity
import com.intoverflown.apiauthdemo.databinding.ActivityLoginBinding
import com.intoverflown.apiauthdemo.login.models.ViewModel
import com.intoverflown.apiauthdemo.login.models.ViewModelFactory
import com.intoverflown.apiauthdemo.network.ApiClient
import com.intoverflown.apiauthdemo.network.ApiInterface
import com.intoverflown.apiauthdemo.network.ApiResponseHandler
import com.intoverflown.apiauthdemo.repository.AuthRepository

class LoginActivity : AppCompatActivity() {

    private var binding : ActivityLoginBinding? = null
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val viewModelFactory = ViewModelFactory(AuthRepository(ApiClient().buildApi(ApiInterface::class.java)))
        viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)

        binding!!.loginBtn.setOnClickListener {
            val username = binding!!.username.text.toString().trim()
            val password = binding!!.password.text.toString().trim()

            viewModel.login(username, password)

            viewModel.loginResponse.observe(this, Observer {
                when (it) {
                    is ApiResponseHandler.Success -> {
                        // print results
                        Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                    }

                    is ApiResponseHandler.Failure -> {
                        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }
}