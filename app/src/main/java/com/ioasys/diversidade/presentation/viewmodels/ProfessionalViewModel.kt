package com.ioasys.diversidade.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ioasys.diversidade.data.remote.repository.ProfessionalRepository
import com.ioasys.diversidade.domain.models.ProfessionalsList
import com.ioasys.diversidade.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProfessionalViewModel @Inject constructor(
    private val professionalRepository: ProfessionalRepository
): ViewModel() {

    val professionals: MutableLiveData<ViewState<ProfessionalsList>> = MutableLiveData()

    fun loadProfessionals() = viewModelScope.launch {
        try {
            val response =  professionalRepository.loadProfessionals()
            professionals.value = handleProfessionals(response)
        } catch (e: Exception) {
            Log.i("responseError", e.toString())
        }
    }

    private fun handleProfessionals(res: Response<ProfessionalsList>): ViewState<ProfessionalsList> {
        professionals.value = ViewState.Loading()
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