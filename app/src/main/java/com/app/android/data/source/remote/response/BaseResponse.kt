package com.app.android.data.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 *
 * @author at-vinhhuynh.
 */
data class BaseResponse<T>(@SerializedName("message") var message: String,
                           @SerializedName("error") var error: List<String>,
                           @SerializedName("data") var data: T)
