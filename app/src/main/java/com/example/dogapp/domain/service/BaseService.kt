package com.example.dogapp.domain.service

import android.util.Log
import com.example.dogapp.core.result.InternetResult
import retrofit2.Response
import java.lang.Exception

open class BaseService {
    suspend fun<T> callApi(call: suspend () -> Response<T>): InternetResult<T> {
        val response: Response<T>
        try {
            response = call.invoke()
        }
        catch (t: Throwable) {
            return InternetResult.Failed(exception = Exception(t.message))
        }
        return if (response.isSuccessful) {
            if (response.body() == null) {
                InternetResult.Failed(exception = Exception("Response with empty body"))
            } else {
                InternetResult.Success<T>(response.body()!!)
            }
        } else {
            val errorBody = response.errorBody().toString()
            Log.e("Tuan", "callApi:$errorBody")
            InternetResult.Failed(exception = Exception(errorBody))
        }
    }
}