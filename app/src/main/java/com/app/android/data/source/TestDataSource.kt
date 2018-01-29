package com.app.android.data.source

import com.app.android.data.model.Test
import io.reactivex.Single

interface TestDataSource {
    fun getData(): Single<List<Test>>

}
