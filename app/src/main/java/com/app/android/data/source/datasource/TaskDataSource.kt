package com.app.android.data.source.datasource

import com.app.android.data.model.Task
import io.reactivex.Observable

/**
 *
 * @author at-vinhhuynh
 */
interface TaskDataSource {

    /**
     * This method use to get list task
     *
     * @param key firebase api key
     * @param requestBody body
     */
    fun getListTask(): Observable<List<Task>>
}
