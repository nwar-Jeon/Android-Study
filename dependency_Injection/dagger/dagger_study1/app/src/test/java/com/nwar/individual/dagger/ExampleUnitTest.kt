package com.nwar.individual.dagger

import com.nwar.individual.dagger.simpleExample.User
import com.nwar.individual.dagger.simpleExample.run
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(run().user.toString(), User(1000,"이름").toString())
    }
}
