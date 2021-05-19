package com.ioasys.diversidade.data

import android.util.Log
import com.ioasys.diversidade.data.network.MyApi
import com.ioasys.diversidade.models.User
import com.ioasys.diversidade.models.UserCredentials
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val myApi: MyApi
) {

    suspend fun signIn(email: String, password: String): Response<User> {
        return myApi.signIn(UserCredentials(email, password))
    }

}