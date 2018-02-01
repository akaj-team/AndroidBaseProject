package com.app.android.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.app.android.data.source.LoginRepository
import com.app.android.data.source.remote.response.LoginResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    private val loginRepository = LoginRepository()
    private val ui = MainActivityUI()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui.setContentView(this)
        viewModel = MainActivityViewModel(LoginRepository())
        viewModel.getProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleGetProfileSucess, this::handleGetProfileError)
    }

    private fun handleGetProfileSucess(loginResponse: LoginResponse) {
        Log.d("VVVV", loginResponse.name)
    }

    private fun handleGetProfileError(throwable: Throwable) {
        Log.d("VVVV", throwable.message)
    }
}
