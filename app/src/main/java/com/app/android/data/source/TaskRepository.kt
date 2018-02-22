package com.app.android.data.source

import com.app.android.data.source.datasource.TaskDataSource
import com.app.android.data.source.remote.TaskRemoteDataSource
import com.app.android.data.source.remote.response.TaskListResponse
import io.reactivex.Observable

/**
 *
 * @author at-vinhhuynh
 */
class TaskRepository : TaskDataSource {

    private val taskRemoteDataSource: TaskRemoteDataSource = TaskRemoteDataSource()

    override fun getListTask(): Observable<TaskListResponse> = taskRemoteDataSource.getListTask()
}
