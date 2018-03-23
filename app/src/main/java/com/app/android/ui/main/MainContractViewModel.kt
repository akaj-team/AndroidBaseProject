package com.app.android.ui.main

import android.support.v7.util.DiffUtil
import com.app.android.data.model.Task
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

/**
 * Copyright © 2018 AsianTech inc.
 * Created by trung.nguyen on 3/23/18.
 */
interface MainContractViewModel {
    fun getProgressbarStatus(): BehaviorSubject<Boolean>
    fun updateListTask(): PublishSubject<DiffUtil.DiffResult>
    fun getListTask(): Observable<Unit>
}
