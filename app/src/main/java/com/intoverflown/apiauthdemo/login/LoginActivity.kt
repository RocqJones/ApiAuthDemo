package com.intoverflown.apiauthdemo.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.intoverflown.apiauthdemo.MainActivity
import com.intoverflown.apiauthdemo.data.LoginResponse
import com.intoverflown.apiauthdemo.data.SharedPreferenceManager
import com.intoverflown.apiauthdemo.databinding.ActivityLoginBinding
import com.intoverflown.apiauthdemo.utils.Constants
import org.json.JSONException
import org.json.JSONObject


class LoginActivity : AppCompatActivity() {

    private var TAG = "LoginActivity- "
    private var binding: ActivityLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        /** User is logged in */
        if (SharedPreferenceManager.getInstance(this).isLoggedIn()) {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }

        /** Call login method */
        binding!!.loginBtn.setOnClickListener {
            // Endpoint
            val url = Constants.BASE_URL + "Connect/Login"
            // user login
            loginUser(url)
        }
    }

    private fun loginUser(url: String) {
        val queue = Volley.newRequestQueue(applicationContext)
        val jsonObject = JSONObject()

        // get user credentials
        val username = binding!!.username.text.toString().trim()
        val password = binding!!.password.text.toString().trim()

        if (username.isNotEmpty() && password.isNotEmpty()) {
            val pd = ProgressDialog(this)
            pd.setMessage("Logging in user!\nPlease wait...")
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            pd.show()

            try {
                jsonObject.put("username", username)
                jsonObject.put("password", password)

                Log.d(TAG + "LoginUser", jsonObject.toString())

            } catch (e: JSONException) {
                e.printStackTrace()
            }

            // connect
            val jsonObjectRequest: JsonObjectRequest = object : JsonObjectRequest(Method.POST,
                    url, jsonObject, Response.Listener { response ->
                try {
                    Log.d("Connection ", response.toString())
                    // Toast.makeText(applicationContext, response.toString(), Toast.LENGTH_LONG).show()
                    pd.hide()

                    //creating a new user object
                    val user = LoginResponse(
                            response.getString("Id"),
                            response.getString("FirstName"),
                            response.getString("LastName"),
                            response.getString("Username"),
                            response.getString("Role"),
                            response.getString("Token")
                    )
                    // Store the user in shared pref
                    Log.d("token b4 SharedPref", user.Token)

                    SharedPreferenceManager.getInstance(applicationContext).userLogin(user)

                    Log.d("token in SharedPref", user.Token)

                    Toast.makeText(applicationContext, "Successful", Toast.LENGTH_SHORT).show()

                    intentHome()

                } catch (e: Exception) {
                    Toast.makeText(this, Log.getStackTraceString(e) + "",
                            Toast.LENGTH_LONG).show()
                    Log.i(TAG, Log.getStackTraceString(e))
                }
            }, Response.ErrorListener { error ->
                Log.e("error", error.toString())
                Toast.makeText(applicationContext, "Login failed!", Toast.LENGTH_SHORT).show()
                pd.hide()
            }) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val params: MutableMap<String, String> = HashMap()
                    params["Content-Type"] = "application/json"
                    return params
                }

                override fun getBodyContentType(): String {
                    return "application/json"
                }
            }

            jsonObjectRequest.retryPolicy = DefaultRetryPolicy(
                    60000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
            queue.add(jsonObjectRequest)
        }
    }

    private fun intentHome() {
        val i = Intent(applicationContext, MainActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(i)
        finish()
    }
}