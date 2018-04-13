package com.app.android.ui.task

import com.app.android.data.model.Task
import com.app.android.data.source.TaskRepository
import com.app.android.ui.task.edittask.TaskDetailContractViewModel
import com.app.android.ui.task.newtask.NewTaskContractViewModel
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import retrofit2.Response

/**
 * Copyright © 2018 AsianTech inc.
 * Created by trung.nguyen on 2/28/18.
 */
class TaskViewModel(private val taskRepository: TaskRepository): NewTaskContractViewModel, TaskDetailContractViewModel {
    private val progressBarStatus = BehaviorSubject.create<Boolean>()

    override fun getTaskDetail(id: Int): Single<Task> {
        return taskRepository.getTaskDetail(id)
                .doOnSubscribe {
                    progressBarStatus.onNext(true)
                }
                .doFinally {
                    progressBarStatus.onNext(false)
                }
    }

    override fun editTask(id: Int, task: Task): Single<Task> {
        return taskRepository.editTask(id, task)
                .doOnSubscribe {
                    progressBarStatus.onNext(true)
                }
                .doFinally {
                    progressBarStatus.onNext(false)
                }
    }

    override fun deleteTask(id: Int): Single<Response<Unit>> {
        return taskRepository.deleteTask(id)
                .doOnSubscribe {
                    progressBarStatus.onNext(true)
                }
                .doFinally {
                    progressBarStatus.onNext(false)
                }
    }

    override fun createTask(task: Task): Single<Task> {
        return taskRepository.createTask(task)
                .doOnSubscribe {
                    progressBarStatus.onNext(true)
                }
                .doFinally {
                    progressBarStatus.onNext(false)
                }
    }

    override fun getProgressbarStatus(): BehaviorSubject<Boolean> {
        return progressBarStatus
    }

    override fun isEnableButton(title: String, description: String): Boolean {
        return !title.isBlank() && !description.isBlank()
    }
}
