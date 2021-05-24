package com.ioasys.diversidade.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ioasys.diversidade.data.DataStoreRepository
import com.ioasys.diversidade.data.Repository
import com.ioasys.diversidade.models.User
import com.ioasys.diversidade.models.UserData
import com.ioasys.diversidade.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val repository: Repository
): ViewModel() {

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

    val userData: MutableLiveData<NetworkResult<User>> = MutableLiveData()
    val registerData: MutableLiveData<NetworkResult<User>> = MutableLiveData()
    val profile: MutableLiveData<NetworkResult<UserData>> = MutableLiveData()

    fun getDetailsAccount(userId: String, token: String) = viewModelScope.launch {
        try {
            val response = repository.remote.getAccountDetails(userId, token)
            Log.i("DEBUG", response.body().toString())
            profile.value = handleAccount(response)
        } catch (e: Exception) {
            Log.i("responseError", e.toString())
        }
    }

    fun signIn(email: String, password: String) = viewModelScope.launch {
        try {
            val response = repository.remote.signIn(email, password)
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
            val response = repository.remote.signUp(email, password, firstName, lastName, telephone)
            registerData.value = handleSignUp(response)
        } catch (e: Exception) {
            Log.i("responseError", e.toString())
        }
    }

    private fun handleAccount(response: Response<UserData>): NetworkResult<UserData> {
        profile.value = NetworkResult.Loading()
        return when {
            response.isSuccessful -> {
                val data = response.body()
                NetworkResult.Success(data!!)
            }
            response.code() == 401 -> {
                NetworkResult.Error("Ocorreu um erro")
            }
            else -> {
                Log.i("userDebug", response.toString())
                NetworkResult.Error("Something went wrong.")
            }
        }
    }

    private fun handleSignUp(response: Response<User>): NetworkResult<User> {
        userData.value = NetworkResult.Loading()
        return when {
            response.isSuccessful -> {
                val data = response.body()
                NetworkResult.Success(data!!)
            }
            response.code() == 409 -> {
                NetworkResult.Error("Email já registrado")
            }
            else -> {
                Log.i("userDebug", response.toString())
                NetworkResult.Error("Something went wrong.")
            }
        }
    }

    private fun handleSignIn(response: Response<User>): NetworkResult<User> {
        userData.value = NetworkResult.Loading()
        return when {
            response.isSuccessful -> {
                val data = response.body()
                NetworkResult.Success(data!!)
            }
            response.code() == 401 -> {
                NetworkResult.Error("Invalid login credentials. Please try again.")
            }
            else -> {
                Log.i("userDebug", response.toString())
                NetworkResult.Error("Something went wrong.")
            }
        }
    }

}