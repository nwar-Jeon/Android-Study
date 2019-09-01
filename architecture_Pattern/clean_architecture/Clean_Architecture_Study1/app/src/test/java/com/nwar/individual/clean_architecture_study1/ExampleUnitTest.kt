package com.nwar.individual.clean_architecture_study1

import com.nwar.individual.clean_architecture_study1.domain.entity.User
import com.nwar.individual.clean_architecture_study1.localData.getDummyData
import org.json.JSONObject
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
        val data = getDummyData()
        assertEquals(data, User(0,"user1",17))
    }
}
