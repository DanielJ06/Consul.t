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
        return myApi.loadProfessionals("Bearer $token")
    }

    suspend fun loadConsults(userId: String, token: String): Response<ConsultsList> {
        return myApi.loadConsults(userId, "Bearer $token")
    }

    suspend fun getAccountDetails(userId: String, token: String): Response<UserData> {
        return myApi.getAccountDetails(userId, "Bearer $token")
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
        return myApi.signUp(RegisterCredentials(
            email = email,
            password = password,
            firstName = firstName,
            lastName = lastName,
            telephone = telephone))
    }

}