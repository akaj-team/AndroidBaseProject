package com.app.android.data.source

import com.app.android.data.source.datasource.LoginDataSource
import com.app.android.data.source.remote.LoginRemoteDataSource
import com.app.android.data.source.remote.request.RegisterRequestBody
import com.app.android.data.source.remote.response.RegisterResponse
import io.reactivex.Single

/**
 *
 * @author at-vinhhuynh
 */
class LoginRepository : LoginDataSource {

    private val loginRemoteDataSource: LoginRemoteDataSource = LoginRemoteDataSource()

    override fun createUser(key: String, body: RegisterRequestBody): Single<RegisterResponse> = loginRemoteDataSource.createUser(key, body)
}
