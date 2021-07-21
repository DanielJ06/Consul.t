package com.ioasys.diversidade.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ioasys.diversidade.data.DataStoreRepository
import com.ioasys.diversidade.domain.repository.AuthRepository
import com.ioasys.diversidade.domain.models.User
import com.ioasys.diversidade.domain.models.UserData
import com.ioasys.diversidade.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    // DataStore
    val readUserInfo = dataStoreRepository.readUserInfo

    fun saveUserInfo(userId: String, userName: String, accessToken: String) =
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveUserInfo(userId, userName, accessToken)
        }

    fun logout() = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.logout()
    }

    // Retrofit

    val userData: MutableLiveData<ViewState<User>> = MutableLiveData()
    val registerData: MutableLiveData<ViewState<User>> = MutableLiveData()
    val profile: MutableLiveData<ViewState<UserData>> = MutableLiveData()

    fun getDetailsAccount(userId: String) = viewModelScope.launch {
        try {
            val response = authRepository.getAccountDetails(userId)
            Log.i("DEBUG", response.body().toString())
            profile.value = handleAccount(response)
        } catch (e: Exception) {
            Log.i("responseError", e.toString())
        }
    }

    fun signIn(email: String, password: String) = viewModelScope.launch {
        try {
            val response = authRepository.signIn(email, password)
            userData.value = handleSignIn(response)
        } catch (e: Exception) {
            Log.i("responseError", e.toString())
        }
    }

    fun signUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        telephone: String
    ) = viewModelScope.launch {
        try {
            val response = authRepository.signUp(email, password, firstName, lastName, telephone)
            registerData.value = handleSignUp(response)
        } catch (e: Exception) {
            Log.i("responseError", e.toString())
        }
    }

    private fun handleAccount(response: Response<UserData>): ViewState<UserData> {
        profile.value = ViewState.Loading()
        return when {
            response.isSuccessful -> {
                val data = response.body()
                ViewState.Success(data!!)
            }
            response.code() == 401 -> {
                ViewState.Error("Ocorreu um erro")
            }
            else -> {
                Log.i("userDebug", response.toString())
                ViewState.Error("Something went wrong.")
            }
        }
    }

    private fun handleSignUp(response: Response<User>): ViewState<User> {
        userData.value = ViewState.Loading()
        return when {
            response.isSuccessful -> {
                val data = response.body()
                ViewState.Success(data!!)
            }
            response.code() == 409 -> {
                ViewState.Error("Email já registrado")
            }
            else -> {
                Log.i("userDebug", response.toString())
                ViewState.Error("Something went wrong.")
            }
        }
    }

    private fun handleSignIn(response: Response<User>): ViewState<User> {
        userData.value = ViewState.Loading()
        return when {
            response.isSuccessful -> {
                val data = response.body()
                ViewState.Success(data!!)
            }
            response.code() == 401 -> {
                ViewState.Error("Invalid login credentials. Please try again.")
            }
            else -> {
                Log.i("userDebug", response.toString())
                ViewState.Error("Something went wrong.")
            }
        }
    }

}