package com.uniqlo.circle

import org.mockito.Mockito

/**
 * Use for mockito
 * @author at-tienhoang on 01/03/18.
 */
open class BaseTest {
    fun <T> any(): T {
        Mockito.any<T>()
        return null as T
    }
}
