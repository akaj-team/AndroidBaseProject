package com.app.android.ui.login

import com.app.android.data.source.LocalRepository
import com.app.android.data.source.LoginRepository
import com.app.android.data.source.remote.response.UserResponse
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

/**
 * Copyright © 2017 AsianTech inc.
 * Created by trung.nguyen on 2/9/18.
 */
class LoginViewModel(private val loginRepository: LoginRepository, private val localRepository: LocalRepository) {

    internal val progressBarStatus: BehaviorSubject<Boolean> = BehaviorSubject.create()

    internal fun login(): Single<UserResponse> {
        progressBarStatus.onNext(true)
        return loginRepository.login()
                .doOnSuccess {
                    progressBarStatus.onNext(false)
                    saveAccessToken(it.accessToken)
                }
                .doOnError {
                    progressBarStatus.onNext(false)
                }
    }

    private fun saveAccessToken(accessToken: String) {
        localRepository.saveAccessToken(accessToken)
    }

    internal fun isEnableNextButton(email: String, password: String): Boolean {
        return !email.isBlank() && !password.isBlank()
    }
}
