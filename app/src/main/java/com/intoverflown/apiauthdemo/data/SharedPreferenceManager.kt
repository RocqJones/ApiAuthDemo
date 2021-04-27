package com.intoverflown.apiauthdemo.data

import android.content.Context
import android.content.Intent
import com.intoverflown.apiauthdemo.login.LoginActivity


class SharedPreferenceManager(context: Context) {

    private val SHARED_PREF_NAME = "volleyLogin"
    private val KEY_USERNAME = "Username"
    private val KEY_FIRSTNAME = "FirstName"
    private val KEY_LASTNAME = "LastName"
    private val KEY_TOKEN = "Token"
    private val KEY_ROLE = "Role"
    private val KEY_ID = "Id"
    private var mInstance: SharedPreferenceManager? = null
    private var ctx: Context? = null

    companion object {
        @Volatile
        private var INSTANCE: SharedPreferenceManager? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: SharedPreferenceManager(context).also {
                    INSTANCE = it
                }
            }
    }

    /** This method will store the user data in shared preferences */
    fun userLogin(loginResponse: LoginResponse) {
        val sharedPreferences = ctx!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_ID, loginResponse.Id)
        editor.putString(KEY_USERNAME, loginResponse.Username)
        editor.putString(KEY_TOKEN, loginResponse.Token)
        editor.putString(KEY_FIRSTNAME, loginResponse.FirstName)
        editor.putString(KEY_LASTNAME, loginResponse.LastName)
        editor.putString(KEY_ROLE, loginResponse.Role)
        editor.apply()
    }

    /** This method will checker whether user is already logged in or not */
    fun isLoggedIn(): Boolean {
        val sharedPreferences = ctx!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_USERNAME, null) != null
    }

    /** This method will serve the logged in user  */
    fun getUser(): LoginResponse {
        val sharedPreferences = ctx!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        return LoginResponse(
            sharedPreferences.getString(KEY_ID, "1aadb13d-5252-47d6-a157-10e8892dbb57")!!,
            sharedPreferences.getString(KEY_USERNAME, null)!!,
            sharedPreferences.getString(KEY_TOKEN, null)!!,
            sharedPreferences.getString(KEY_FIRSTNAME, null)!!,
            sharedPreferences.getString(KEY_LASTNAME, null)!!,
            sharedPreferences.getString(KEY_ROLE, null)!!
        )
    }

    /** This method will logout the user */
    fun logout() {
        val sharedPreferences = ctx!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
        ctx!!.startActivity(Intent(ctx, LoginActivity::class.java))
    }
}