package com.helper.common

import kotlinx.coroutines.CoroutineScope

sealed class NetworkResult<T>() {

    class Success<T>(val data: T) : NetworkResult<T>()

    class Error<T>(val code: Int, val message: String? = null) : NetworkResult<T>()
}

suspend inline fun <T> CoroutineScope.safeCall(crossinline request: suspend () -> Response<T>): NetworkResult<T> {
    // try catch для обработки рантайм ошибок
    return try {
        val response = request()
        // если другая ошибка от бека, то возвращаем ошибку
        if (response.metaData.isSuccessful().not()) {
            NetworkResult.Error(
                response.metaData.code,
                response.metaData.errorMessage ?: ""
            )
        } else {
            NetworkResult.Success(response.data)
        }
    } catch (th: Throwable) {
        NetworkResult.Error(200, "200 error")
    }
}


suspend inline fun <T1 : Any, T2 : Any, R : Any> safeLet(
    response1: NetworkResult<T1>,
    response2: NetworkResult<T2>,
    block: suspend (NetworkResult.Success<T1>, NetworkResult.Success<T2>) -> R?
): R? {
    return if (response1 is NetworkResult.Success && response2 is NetworkResult.Success) block(
        response1,
        response2
    ) else null
}


data class Response<T>(
    val data: T,
    val metaData: MetaData
)

data class MetaData(
    val code: Int,
    val errorMessage: String?
) {
    fun isSuccessful(): Boolean = this.code == 0
}
