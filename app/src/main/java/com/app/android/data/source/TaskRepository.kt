package com.app.android.data.source

import com.app.android.data.model.Task
import com.app.android.data.source.datasource.TaskDataSource
import com.app.android.data.source.remote.TaskRemoteDataSource
import io.reactivex.Observable

/**
 *
 * @author at-vinhhuynh
 */
class TaskRepository : TaskDataSource {

    private val taskRemoteDataSource: TaskRemoteDataSource = TaskRemoteDataSource()

    override fun getListTask(): Observable<List<Task>> = taskRemoteDataSource.getListTask()

    override fun createTask(task: Task): Observable<Task> = taskRemoteDataSource.createTask(task)
}
