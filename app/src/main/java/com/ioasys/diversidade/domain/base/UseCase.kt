package com.ioasys.diversidade.domain.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect


abstract class UseCase<T, in Params> {

    abstract suspend fun execute(params: Params? = null): Flow<T>

    suspend operator fun invoke(
        params: Params? = null,
        onError: (Throwable) -> Unit = {},
        onSuccess: (T) -> Unit = {}
    ) {
        try {
            execute(params).collect {
                onSuccess(it)
            }
        } catch (e: Exception) {
            onError(e)
        }
    }

}