package com.uniqlo.circle

import com.uniqlo.circle.data.source.remote.network.CustomCall
import com.uniqlo.circle.data.source.remote.network.CustomCallback
import okhttp3.Request
import retrofit2.Response

/**
 *
 * @author at-hoavo.
 */
interface CustomTestCall<T> : CustomCall<T> {
    override fun enqueue(callback: CustomCallback<T>) = Unit
    override fun isCanceled() = false
    override fun isExecuted() = true
    override fun cancel() = Unit
    override fun execute() = Response.success<T>(null)
    override fun clone() = this
    override fun request() = Request.Builder().build()
}
