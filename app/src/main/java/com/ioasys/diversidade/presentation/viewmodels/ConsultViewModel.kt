package com.ioasys.diversidade.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ioasys.diversidade.data.Repository
import com.ioasys.diversidade.domain.models.ConsultsList
import com.ioasys.diversidade.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ConsultViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    val consults: MutableLiveData<ViewState<ConsultsList>> = MutableLiveData()
    val requestStatus: MutableLiveData<ViewState<Any>> = MutableLiveData()

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

    private fun handleConsultRequest(res: Response<Any>): ViewState<Any> {
        return when {
            res.isSuccessful -> {
                val data = res.body()!!
                ViewState.Success(data)
            } else -> {
                ViewState.Error(res.toString())
            }
        }
    }

    private fun handleConsults(res: Response<ConsultsList>): ViewState<ConsultsList> {
        return when {
            res.isSuccessful -> {
                val data = res.body()!!
                ViewState.Success(data)
            }
            res.code() == 401 -> {
                ViewState.Error("Invalid token. Please logout and signIn again.")
            }
            else -> {
                Log.i("userDebug", res.toString())
                ViewState.Error("Something went wrong.")
            }
        }
    }
}