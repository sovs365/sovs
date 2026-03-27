package com.university.voting.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.university.voting.api.AuthResponse
import com.university.voting.api.LoginRequest
import com.university.voting.api.LoginCodeResponse
import com.university.voting.api.RegisterRequest
import com.university.voting.repository.VotingRepository
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = VotingRepository()
    private val prefs = application.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    private val _loginResult = MutableLiveData<Result<AuthResponse>>()
    val loginResult: LiveData<Result<AuthResponse>> = _loginResult

    private val _loginCodeResult = MutableLiveData<Result<LoginCodeResponse>>()
    val loginCodeResult: LiveData<Result<LoginCodeResponse>> = _loginCodeResult

    private val _registerResult = MutableLiveData<Result<AuthResponse>>()
    val registerResult: LiveData<Result<AuthResponse>> = _registerResult

    private val _resetResult = MutableLiveData<Result<String>>()
    val resetResult: LiveData<Result<String>> = _resetResult

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun login(username: String, password: String) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = repository.login(LoginRequest(username, password))
            _loginCodeResult.postValue(result)
            _isLoading.postValue(false)
        }
    }

    fun verifyLoginCode(email: String, code: String) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = repository.verifyLoginCode(email, code)
            result.onSuccess { authResponse ->
                saveAuth(authResponse)
            }
            _loginResult.postValue(result)
            _isLoading.postValue(false)
        }
    }

    fun register(request: RegisterRequest) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = repository.register(request)
            result.onSuccess { authResponse ->
                saveAuth(authResponse)
            }
            _registerResult.postValue(result)
            _isLoading.postValue(false)
        }
    }

    fun sendPasswordResetCode(email: String) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = repository.sendPasswordResetCode(email)
            _resetResult.postValue(result.map { "Code sent to email" })
            _isLoading.postValue(false)
        }
    }

    fun resetPassword(email: String, code: String, newPassword: String, confirmPassword: String) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = repository.resetPassword(email, code, newPassword, confirmPassword)
            _resetResult.postValue(result.map { it.message })
            _isLoading.postValue(false)
        }
    }

    private fun saveAuth(authResponse: AuthResponse) {
        prefs.edit()
            .putString("token", authResponse.token)
            .putString("user_id", authResponse.user.userId)
            .putString("user_role", authResponse.user.role)
            .putString("user_name", authResponse.user.fullName)
            .putString("username", authResponse.user.username)
            .apply()
    }

    fun getToken(): String = prefs.getString("token", "") ?: ""
    fun getUserId(): String = prefs.getString("user_id", "") ?: ""
    fun getUserRole(): String = prefs.getString("user_role", "") ?: ""
    fun getUserName(): String = prefs.getString("user_name", "") ?: ""
    fun isLoggedIn(): Boolean = getToken().isNotEmpty()

    fun logout() {
        prefs.edit().clear().apply()
    }
}
