package com.nwar.individual.dagger_study2

import org.junit.Test

import org.junit.Assert.*
import javax.inject.Inject

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Inject
    lateinit var string : String
    @Test
    fun addition_isCorrect() {
        println(AppApplication().buildComponent().getString())
        assertEquals(4, 2 + 2)
    }
}
