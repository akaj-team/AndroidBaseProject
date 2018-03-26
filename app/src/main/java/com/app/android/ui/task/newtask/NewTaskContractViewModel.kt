package com.app.android.ui.task.newtask

import com.app.android.data.model.Task
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

/**
 * Copyright © 2018 AsianTech inc.
 * Created by trung.nguyen on 3/26/18.
 */
interface NewTaskContractViewModel {
    fun createTask(task: Task): Single<Task>
    fun getProgressbarStatus(): BehaviorSubject<Boolean>
}
