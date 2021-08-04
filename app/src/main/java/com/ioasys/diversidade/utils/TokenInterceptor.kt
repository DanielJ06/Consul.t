package com.ioasys.diversidade.utils

import android.content.Context
import com.ioasys.diversidade.utils.Constants.Companion.ACCESS_TOKEN
import com.ioasys.diversidade.utils.Constants.Companion.SHARED_PREFERENCE
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    @ApplicationContext private val context: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)
        val token = sharedPreferences.getString(ACCESS_TOKEN, "")

        token?.let {
            request = request.newBuilder()
                .addHeader("Authorization", "Bearer $it")
                .build()
        }
        return chain.proceed(request)
    }

}