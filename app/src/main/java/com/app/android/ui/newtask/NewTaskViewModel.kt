package com.app.android.ui.newtask

import com.app.android.data.model.Task
import com.app.android.data.source.TaskRepository
import io.reactivex.subjects.BehaviorSubject

/**
 * Copyright © 2018 AsianTech inc.
 * Created by trung.nguyen on 2/28/18.
 */
class NewTaskViewModel(private val taskRepository: TaskRepository) {
    internal var progressBarStatus = BehaviorSubject.create<Boolean>()

    internal fun createTask(task: Task) = taskRepository.createTask(task)
            .doOnSubscribe {
                progressBarStatus.onNext(true)
            }
            .doFinally {
                progressBarStatus.onNext(false)
            }

    internal fun isEnableSubmitButton(title: String, description: String) = !title.isBlank() && !description.isBlank()
}
