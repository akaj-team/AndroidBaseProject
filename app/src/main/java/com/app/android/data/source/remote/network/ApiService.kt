package com.app.android.data.source.remote.network

import com.app.android.data.source.remote.request.RegisterRequestBody
import com.app.android.data.source.remote.response.RegisterResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

/**
 *
 * @author at-vinhhuynh
 */
interface ApiService {

    @POST("/identitytoolkit/v3/relyingparty/signupNewUser")
    fun createUser(@Query("key") key: String, @Body requestBody: RegisterRequestBody): Single<RegisterResponse>
}
