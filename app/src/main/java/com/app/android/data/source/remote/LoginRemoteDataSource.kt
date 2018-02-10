package com.app.android.data.source.remote

import com.app.android.data.source.datasource.LoginDataSource
import com.app.android.data.source.remote.network.ApiClient
import com.app.android.data.source.remote.network.ApiService
import com.app.android.data.source.remote.response.LoginResponse
import com.app.android.data.source.remote.response.UserResponse
import io.reactivex.Single

/**
 *
 * @author at-vinhhuynh
 */
class LoginRemoteDataSource(private val apiService: ApiService) : LoginDataSource {

    constructor() : this(ApiClient.getInstance(null).service)

    override fun getProfile(): Single<LoginResponse> = apiService.getProfile()


    override fun login(): Single<UserResponse> = apiService.login()
}
