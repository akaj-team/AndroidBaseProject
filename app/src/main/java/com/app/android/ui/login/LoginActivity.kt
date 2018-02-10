package com.app.android.ui.login

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import com.app.android.R
import com.app.android.data.source.LocalRepository
import com.app.android.data.source.LoginRepository
import com.app.android.data.source.remote.response.UserResponse
import com.app.android.extension.observeOnUiThread
import com.app.android.ui.main.MainActivity
import com.uniqlo.circle.ui.base.BaseActivity
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.textColor

/**
 * Copyright © 2017 AsianTech inc.
 * Created by trung.nguyen on 2/9/18.
 */
class LoginActivity : BaseActivity() {

    private val ui = LoginActivityUI()
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui.setContentView(this)
        loginViewModel = LoginViewModel(LoginRepository(), LocalRepository())
    }

    override fun onBindViewModel() {
        addDisposables(
                loginViewModel.progressBarStatus
                        .observeOnUiThread()
                        .subscribe(this::handleProgressBarStatus))
    }

    internal fun onTextChangeListener(email: String, password: String) {
        if (loginViewModel.isEnableNextButton(email, password) && loginViewModel.isValidateEmail(email)) {
            ui.tvNext.isEnabled = true
            ui.tvNext.textColor = ContextCompat.getColor(this, android.R.color.holo_red_light)
        } else {
            ui.tvNext.isEnabled = false
            ui.tvNext.textColor = ContextCompat.getColor(this, R.color.red_blur)
        }
    }

    internal fun onClickListener(view: View) {
        (view == ui.tvNext).let {
            loginViewModel.login()
                    .observeOnUiThread()
                    .subscribe(this::handleLoginSuccess, this::handleLoginError)
        }
    }

    private fun handleLoginSuccess(user: UserResponse) {
        startActivity<MainActivity>()
    }

    private fun handleLoginError(t: Throwable) {

    }

    private fun handleProgressBarStatus(isShow: Boolean) {
        if (isShow) {
            visibleProgressBar(ui.progressBar)
        } else {
            invisibleProgressBar(ui.progressBar)
        }
    }

    private fun visibleProgressBar(progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    private fun invisibleProgressBar(progressBar: ProgressBar) {
        progressBar.visibility = View.GONE
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}
