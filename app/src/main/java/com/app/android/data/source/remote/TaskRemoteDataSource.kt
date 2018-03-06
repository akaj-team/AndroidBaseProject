package com.app.android.data.source.remote

import com.app.android.data.model.Task
import com.app.android.data.source.datasource.TaskDataSource
import com.app.android.data.source.remote.network.ApiClient
import com.app.android.data.source.remote.network.ApiService
import io.reactivex.Observable
import io.reactivex.Single

/**
 *
 * @author at-vinhhuynh
 */
class TaskRemoteDataSource(private val apiService: ApiService = ApiClient.getInstance().service) : TaskDataSource {

    override fun getListTask(): Observable<List<Task>> = apiService.getTasks()

    override fun createTask(task: Task): Single<Task> = apiService.createTask(task)

    override fun getTaskDetail(id: Int): Single<Task> = apiService.getTaskDetail(id)

    override fun editTask(id: Int, task: Task): Single<Task> = apiService.editTask(id, task)

    override fun deleteTask(id: Int): Single<Unit> = apiService.deleteTask(id)
}
