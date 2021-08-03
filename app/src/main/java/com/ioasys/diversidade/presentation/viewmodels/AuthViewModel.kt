package com.ioasys.diversidade.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ioasys.diversidade.data.DataStoreRepository
import com.ioasys.diversidade.domain.models.User
import com.ioasys.diversidade.domain.models.UserData
import com.ioasys.diversidade.domain.repository.AuthRepository
import com.ioasys.diversidade.domain.useCases.SignInUseCase
import com.ioasys.diversidade.domain.useCases.SignUpUseCase
import com.ioasys.diversidade.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val authRepository: AuthRepository,
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel(), LifecycleObserver {

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
        userData.value = ViewState.Loading()
        signInUseCase(
            params = SignInUseCase.Params(email, password),
            onSuccess = {
                userData.value = ViewState.Success(it)
            },
            onError = {
                userData.value = ViewState.Error(it.message.toString())
            }
        )
    }

    fun signUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        telephone: String
    ) = viewModelScope.launch {
        signUpUseCase(
            params = SignUpUseCase.Params(email, password, firstName, lastName, telephone),
            onSuccess = {
                registerData.value = ViewState.Success(it)
            },
            onError = {
                registerData.value = ViewState.Error(it.message.toString())
            }
        )
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

}