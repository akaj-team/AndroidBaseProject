package com.app.android.data.source.remote.network

import com.app.android.data.model.Task
import io.reactivex.Observable
import retrofit2.http.GET

/**
 *
 * @author at-vinhhuynh
 */
interface ApiService {

    /**
     * This method use to create new user
     */
    @GET("/api/tasks")
    fun getTasks(): Observable<List<Task>>
}
