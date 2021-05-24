package com.ioasys.diversidade.data

import android.util.Log
import com.ioasys.diversidade.data.network.MyApi
import com.ioasys.diversidade.models.*
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val myApi: MyApi
) {

    suspend fun loadProfessionals(token: String): Response<ProfessionalsList> {
        return myApi.loadProfessionals("bearer $token")
    }

    suspend fun signIn(email: String, password: String): Response<User> {
        return myApi.signIn(UserCredentials(email, password))
    }

    suspend fun signUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        telephone: String
    ): Response<User> {
        return myApi.signUp(RegisterCredentials(email, password, firstName, lastName, telephone))
    }

}