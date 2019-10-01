package com.nwar.individual.mockito

import org.junit.Test

import org.junit.Assert.*

import org.mockito.Mockito.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val mockList = mock(Entity::class.java)
        `when`(mockList.getEntityId()).thenReturn(6)
        assertEquals(mockList.getEntityId(), 6)
    }
}
