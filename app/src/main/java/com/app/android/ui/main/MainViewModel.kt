package com.app.android.ui.main

import android.support.v7.util.DiffUtil
import com.app.android.data.model.Task
import com.app.android.data.source.TaskRepository
import com.app.android.ui.base.Diff
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

/**
 *
 * @author at-vinhhuynh
 */
class MainViewModel(private val taskRepository: TaskRepository) : MainContractViewModel {

    private var progressBarStatus = BehaviorSubject.create<Boolean>()
    private val updateListTask = PublishSubject.create<DiffUtil.DiffResult>()
    private var tasks = mutableListOf<Task>()

    override fun getListTaskFromApi(): Observable<Unit> {
        return taskRepository.getListTask()
                .doOnSubscribe {
                    progressBarStatus.onNext(true)
                }
                .doFinally {
                    progressBarStatus.onNext(false)
                }
                .onErrorReturn {
                    listOf()
                }
                .observeOn(Schedulers.computation())
                .map {
                    val diff = Diff(tasks, it)
                            .areItemsTheSame { oldItem, newItem ->
                                oldItem.id == newItem.id
                            }
                            .areContentsTheSame { oldItem, newItem ->
                                oldItem.title == newItem.title
                                oldItem.description == newItem.description
                                oldItem.isDone == newItem.isDone
                                oldItem.createTime == newItem.createTime
                                oldItem.updateTime == newItem.updateTime
                            }
                            .calculateDiff()

                    tasks.clear()
                    tasks.addAll(it)
                    updateListTask.onNext(diff)
                }
    }

    override fun getProgressbarStatus(): BehaviorSubject<Boolean> {
        return progressBarStatus
    }

    override fun updateListTask(): PublishSubject<DiffUtil.DiffResult> {
        return updateListTask
    }

    override fun getListTask(): MutableList<Task> {
        return tasks
    }
}
