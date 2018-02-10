package com.app.android.data.source.remote.network

import com.app.android.data.source.remote.response.LoginResponse
import com.app.android.data.source.remote.response.UserResponse
import io.reactivex.Single
import retrofit2.http.GET

/**
 *
 * @author at-vinhhuynh
 */
interface ApiService {
    /**
     * This method get github profile
     *
     * @param none
     */
    @GET("user?access_token=???")
    fun getProfile(): Single<LoginResponse>

    @GET("user?access_token=???")
    fun login(): Single<UserResponse>
}
