package ir.codroid.books.data.util

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseApiResponse {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> =
        withContext(Dispatchers.IO) {
            try {
                val response = apiCall()
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        return@withContext NetworkResult.Success(body , null)
                    }
                }
                return@withContext error((response.errorBody()?.string()?: "Unknown error"))
            } catch (e: Exception) {
                Log.e("api call", e.message ?: e.toString())
                return@withContext error(e.message ?: e.toString())
            }
        }


    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error(message = "APi call failed : $errorMessage")

}