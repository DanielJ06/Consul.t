package com.ioasys.diversidade.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ioasys.diversidade.data.Repository
import com.ioasys.diversidade.models.Professional
import com.ioasys.diversidade.models.ProfessionalsList
import com.ioasys.diversidade.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProfessionalViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    val professionals: MutableLiveData<NetworkResult<ProfessionalsList>> = MutableLiveData()

    fun loadProfessionals(token: String) = viewModelScope.launch {
        try {
            val response = repository.remote.loadProfessionals(token)
            professionals.value = handleProfessionals(response)
        } catch (e: Exception) {
            Log.i("responseError", e.toString())
        }
    }

    private fun handleProfessionals(res: Response<ProfessionalsList>): NetworkResult<ProfessionalsList> {
        professionals.value = NetworkResult.Loading()
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