package com.app.android.api

import com.app.android.api.element.ExampleApiTest
import com.app.android.data.source.remote.network.ApiClient
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockWebServer
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.runner.RunWith
import org.junit.runners.Suite

/**

 * @author at-hoavo.
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(
        ExampleApiTest::class
)
class ApiSuiteTest {

    companion object {
        internal val server = MockWebServer()
        internal lateinit var baseUrl: HttpUrl

        @BeforeClass
        @JvmStatic
        fun beforeClass() {
            ApiClient.clearInstance()
            try {
                server.start()
            } catch (e: IllegalStateException) {
                println(e.message)
            }
            baseUrl = server.url("/")
        }

        @AfterClass
        @JvmStatic
        fun afterClass() {
            server.shutdown()
            ApiClient.clearInstance()
        }
    }
}