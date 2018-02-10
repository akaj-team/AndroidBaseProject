package com.app.android.data.source.datasource

import com.app.android.data.source.remote.response.LoginResponse
import com.app.android.data.source.remote.response.UserResponse
import io.reactivex.Single

/**
 *
 * @author at-vinhhuynh
 */
interface LoginDataSource {

    /**
     * This method get github profile
     *
     * @param none
     */
    fun getProfile(): Single<LoginResponse>

    /**
     * This method use to login
     *
     * @param none
     */
    fun login(): Single<UserResponse>
}
