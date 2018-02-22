package com.app.android.ui.main

import android.support.v7.util.DiffUtil
import com.app.android.data.model.Task
import com.app.android.data.source.TaskRepository
import com.app.android.data.source.remote.response.TaskListResponse
import com.uniqlo.circle.ui.base.Diff
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

/**
 *
 * @author at-vinhhuynh
 */
class MainActivityViewModel(private val taskRepository: TaskRepository) {
    internal var progressBarStatus: BehaviorSubject<Boolean> = BehaviorSubject.create()
    private val taskObservable = PublishSubject.create<Unit>()
    internal val updateListTask = PublishSubject.create<DiffUtil.DiffResult>()
    internal var tasks = mutableListOf<Task>()

    init {
        iniTasks()
    }

    private fun iniTasks() {
        taskObservable
                .observeOn(Schedulers.computation())
                .flatMap {
                    getTasksApi()
                }.doOnNext {
            val diff = Diff(tasks, it.tasks)
                    .areItemsTheSame { oldItem, newItem ->
                        oldItem.id == newItem.id
                    }
                    .calculateDiff()

            tasks.clear()
            tasks.addAll(it.tasks)
            updateListTask.onNext(diff)
        }.subscribe()
    }

    internal fun getTasks() {
        taskObservable.onNext(Unit)
    }

    private fun getTasksApi():
            Observable<TaskListResponse> = taskRepository
            .getListTask()
            .doOnSubscribe { progressBarStatus.onNext(true) }
            .doFinally { progressBarStatus.onNext(false) }
}
