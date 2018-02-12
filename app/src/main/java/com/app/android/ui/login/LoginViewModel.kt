package com.app.android.ui.login

import android.util.Patterns
import com.app.android.data.source.LocalRepository
import com.app.android.data.source.LoginRepository
import com.app.android.data.source.remote.request.RegisterRequestBody
import com.app.android.data.source.remote.response.RegisterResponse
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

/**
 * Copyright © 2017 AsianTech inc.
 * Created by trung.nguyen on 2/9/18.
 */
class LoginViewModel(private val loginRepository: LoginRepository, private val localRepository: LocalRepository) {

    internal val progressBarStatus: BehaviorSubject<Boolean> = BehaviorSubject.create()

    internal fun createUser(key: String, email: String, password: String): Single<RegisterResponse> {
        progressBarStatus.onNext(true)
        return loginRepository.createUser(key, RegisterRequestBody(email, password))
                .doOnSuccess {
                    saveAccessToken(it.idToken)
                }
                .doFinally {
                    progressBarStatus.onNext(false)
                }
    }

    private fun saveAccessToken(accessToken: String) {
        localRepository.saveAccessToken(accessToken)
    }

    internal fun isEnableNextButton(email: String, password: String): Boolean {
        return !email.isBlank() && !password.isBlank()
    }

    internal fun isValidateEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
