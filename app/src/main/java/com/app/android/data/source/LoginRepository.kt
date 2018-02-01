package com.app.android.data.source

import com.app.android.data.source.datasource.LoginDataSource
import com.app.android.data.source.remote.LoginRemoteDataSource
import com.app.android.data.source.remote.response.LoginResponse
import io.reactivex.Single

/**
 *
 * @author at-vinhhuynh
 */
class LoginRepository : LoginDataSource {
    private val loginRemoteDataSource: LoginRemoteDataSource = LoginRemoteDataSource()
    override fun getProfile(): Single<LoginResponse> = loginRemoteDataSource.getProfile()
}
