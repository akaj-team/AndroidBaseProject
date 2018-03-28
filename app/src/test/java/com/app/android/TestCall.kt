package com.uniqlo.circle

import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *
 * @author at-hoavo.
 */
interface TestCall<T> : Call<T> {
    override fun enqueue(callback: Callback<T>?) = Unit
    override fun isCanceled() = false
    override fun isExecuted() = true
    override fun cancel() = Unit
    override fun execute() = Response.success<T>(null)
    override fun clone() = this
    override fun request() = Request.Builder().build()
}
