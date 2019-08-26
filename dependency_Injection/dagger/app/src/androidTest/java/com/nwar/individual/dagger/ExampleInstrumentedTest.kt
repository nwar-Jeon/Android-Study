package com.nwar.individual.dagger

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.nwar.individual.dagger.simpleExample.User

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        assertEquals(User(1000,"이름").toString(),com.nwar.individual.dagger.simpleExample.run().user.toString())
    }
}
