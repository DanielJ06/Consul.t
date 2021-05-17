package com.ioasys.diversidade.viewmodels

import androidx.lifecycle.ViewModel
import com.ioasys.diversidade.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
}