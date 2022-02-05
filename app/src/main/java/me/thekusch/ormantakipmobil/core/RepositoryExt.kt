package me.thekusch.ormantakipmobil.core

import retrofit2.Response


suspend fun <T: Any?> Repository.invokeApi(
    apiInvoke: (suspend () -> T?)
): Resource<T?> {
    return try {
        val invokeResult = apiInvoke.invoke()
        return if(invokeResult is Response<*>) {
            if(invokeResult.code() >= 400) {
                Resource.Error()
            } else {
                Resource.Success(invokeResult)
            }
        } else {
            Resource.Success(invokeResult)
        }
    }catch (t: Throwable) {
        Resource.Error(t.localizedMessage)
    }
}