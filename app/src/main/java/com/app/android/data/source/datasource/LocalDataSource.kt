package com.app.android.data.source.datasource

/**
 * This class contain methods work with data on local
 * @author at-vinhhuynh
 */
interface LocalDataSource {
    /**
     * This method save user access token
     *
     * @param accessToken user access token
     */
    fun saveAccessToken(accessToken: String)
}
