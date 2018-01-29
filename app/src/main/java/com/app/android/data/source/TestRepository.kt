package com.app.android.data.source

import com.app.android.data.model.Test
import com.app.android.data.source.local.TestLocalDataSource
import io.reactivex.Single

class TestRepository : TestDataSource {

    private val testLocalDataSource = TestLocalDataSource()

    override fun getData(): Single<List<Test>> = testLocalDataSource.getData()

}