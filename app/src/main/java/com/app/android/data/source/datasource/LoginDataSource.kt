package com.app.android.data.source.datasource

import com.app.android.data.source.remote.response.LoginResponse
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
}