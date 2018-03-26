package com.app.android.ui.task.edittask

import com.app.android.data.model.Task
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import retrofit2.Response

/**
 * Copyright © 2018 AsianTech inc.
 * Created by trung.nguyen on 3/26/18.
 */
interface TaskDetailContractViewModel {
    fun getTaskDetail(id: Int): Single<Task>
    fun editTask(id: Int, task: Task): Single<Task>
    fun deleteTask(id: Int): Single<Response<Unit>>
    fun getProgressbarStatus(): BehaviorSubject<Boolean>
}
