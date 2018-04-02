package com.app.android.api.element

import com.uniqlo.circle.BaseTest
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is.`is`
import org.junit.Test

/**
 *
 * @author at-vinhhuynh
 */
@Suppress("IllegalIdentifier")
class ExampleApiTest : BaseTest() {

    @Test
    fun `Given a test variable  - When do nothing - Then return correct value`() {
        /* Given */
        val result = false

        /* When */


        /* Then */
        MatcherAssert.assertThat(result, `is`(false))
    }
}
