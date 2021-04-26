package com.intoverflown.apiauthdemo.login.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.intoverflown.apiauthdemo.repository.AuthRepository

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class ViewModelFactory(private val repository: AuthRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModel(repository) as T
    }
}