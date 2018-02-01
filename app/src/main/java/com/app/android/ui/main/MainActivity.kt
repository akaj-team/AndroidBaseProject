package com.app.android.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.Log.d
import com.app.android.data.source.LoginRepository
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
                .map {
                    d("VVVV",Thread.currentThread().name)
                    it
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("VVVV", it.name)
                }, {
                    Log.d("VVVV", "Throwable: " + it)
                })
    }
}
