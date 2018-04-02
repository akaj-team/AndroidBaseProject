package com.app.android.viewmodel

import com.uniqlo.circle.BaseTest
import com.uniqlo.circle.util.RxSchedulersOverrideRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

/**
 *
 * @author at-vinhhuynh
 */
@Suppress("IllegalIdentifier")
class ExampleViewModelTest : BaseTest() {

    @get:Rule
    val rule = RxSchedulersOverrideRule()
    
    @Before
    fun beforeTest() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun `Given  - When  - Then `() {
        /* Given */


        /* When */


        /* Then */

    }
}