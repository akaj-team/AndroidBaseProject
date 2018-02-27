package com.app.android.data.source.remote

import com.app.android.data.model.Task
import com.app.android.data.source.datasource.TaskDataSource
import com.app.android.data.source.remote.network.ApiClient
import com.app.android.data.source.remote.network.ApiService
import io.reactivex.Observable

/**
 *
 * @author at-vinhhuynh
 */
class TaskRemoteDataSource(private val apiService: ApiService = ApiClient.getInstance().service) : TaskDataSource {

    override fun getListTask(): Observable<List<Task>> = apiService.getTasks()
}
