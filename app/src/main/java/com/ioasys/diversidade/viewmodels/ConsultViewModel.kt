package com.ioasys.diversidade.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ioasys.diversidade.data.Repository
import com.ioasys.diversidade.models.Consult
import com.ioasys.diversidade.models.ConsultsList
import com.ioasys.diversidade.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ConsultViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    val consults: MutableLiveData<NetworkResult<ConsultsList>> = MutableLiveData()
    val requestStatus: MutableLiveData<NetworkResult<Any>> = MutableLiveData()

    fun loadConsults(userId: String) = viewModelScope.launch {
        try {
            val res = repository.remote.loadConsults(userId)
            consults.value = handleConsults(res)
        } catch (e: Exception ) {
            Log.i("DEBUG", e.toString())
        }
    }

    fun requestConsult(
        userId: String,
        professionalId: String,
        reason: String
    ) = viewModelScope.launch {
        try {
            val res = repository.remote.requestConsults(userId, professionalId, reason)
            requestStatus.value = handleConsultRequest(res)
        } catch (e: Exception) {
            Log.i("DEGUB", e.toString())
        }
    }

    private fun handleConsultRequest(res: Response<Any>): NetworkResult<Any> {
        return when {
            res.isSuccessful -> {
                val data = res.body()!!
                NetworkResult.Success(data)
            } else -> {
                NetworkResult.Error(res.toString())
            }
        }
    }

    private fun handleConsults(res: Response<ConsultsList>): NetworkResult<ConsultsList> {
        return when {
            res.isSuccessful -> {
                val data = res.body()!!
                NetworkResult.Success(data)
            }
            res.code() == 401 -> {
                NetworkResult.Error("Invalid token. Please logout and signIn again.")
            }
            else -> {
                Log.i("userDebug", res.toString())
                NetworkResult.Error("Something went wrong.")
            }
        }
    }
}