package com.app.android.data.source.local

import com.app.android.data.model.Test
import io.reactivex.Single

class TestLocalDataSource {

    fun getData() = Single.just(listOf(Test()))
}