package com.app.android.data.source.remote.network

import com.app.android.data.model.Task
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 *
 * @author at-vinhhuynh
 */
interface ApiService {

    /**
     * This method use to get list task
     */
    @GET("api/tasks")
    fun getTasks(): Observable<List<Task>>

    /**
     * This method use to create new task
     *
     * @param task task
     */
    @POST("api/tasks")
    fun createTask(@Body task: Task): Observable<Task>
}
