package com.app.android.ui.task

import com.app.android.data.model.Task
import com.app.android.data.source.TaskRepository
import io.reactivex.subjects.BehaviorSubject

/**
 * Copyright © 2018 AsianTech inc.
 * Created by trung.nguyen on 2/28/18.
 */
class TaskViewModel(private val taskRepository: TaskRepository) {
    internal var progressBarStatus = BehaviorSubject.create<Boolean>()

    internal fun createTask(task: Task) = taskRepository.createTask(task)
            .doOnSubscribe {
                progressBarStatus.onNext(true)
            }
            .doFinally {
                progressBarStatus.onNext(false)
            }

    internal fun getTaskDetail(id: Int) = taskRepository.getTaskDetail(id)
            .doOnSubscribe {
                progressBarStatus.onNext(true)
            }
            .doFinally {
                progressBarStatus.onNext(false)
            }

    internal fun editTask(id: Int, task: Task) = taskRepository.editTask(id, task)
            .doOnSubscribe {
                progressBarStatus.onNext(true)
            }
            .doFinally {
                progressBarStatus.onNext(false)
            }

    internal fun deleteTask(id: Int) = taskRepository.deleteTask(id)
            .doOnSubscribe {
                progressBarStatus.onNext(true)
            }
            .doFinally {
                progressBarStatus.onNext(false)
            }

    internal fun isEnableButton(title: String, description: String) = !title.isBlank() && !description.isBlank()
}
