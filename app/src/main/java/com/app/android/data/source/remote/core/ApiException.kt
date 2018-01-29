package com.app.android.data.source.remote.core

import com.google.gson.annotations.SerializedName

/**
 *
 * @author at-vinhhuynh
 */
data class ApiException(
        @SerializedName("message") val messageError: String,
        @SerializedName("errors") val errors: MutableList<String>) : Throwable(messageError) {
    var statusCode: Int? = null
}
