package com.ioasys.diversidade.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ioasys.diversidade.data.Repository
import com.ioasys.diversidade.models.User
import com.ioasys.diversidade.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    val userData: MutableLiveData<NetworkResult<User>> = MutableLiveData()

    fun signIn(email: String, password: String) = viewModelScope.launch {
        try {
            val response = repository.remote.signIn(email, password)
            userData.value = handleSignIn(response)
        } catch (e: Exception) {
            Log.i("responseError", e.toString())
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