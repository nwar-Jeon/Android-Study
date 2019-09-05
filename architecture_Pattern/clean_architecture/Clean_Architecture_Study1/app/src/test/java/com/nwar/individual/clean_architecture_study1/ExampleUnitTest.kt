package com.nwar.individual.clean_architecture_study1

import androidx.lifecycle.ViewModelProviders
import com.nwar.individual.clean_architecture_study1.di.DaggerMainComponent
import com.nwar.individual.clean_architecture_study1.di.MainModule
import com.nwar.individual.clean_architecture_study1.domain.entity.User
import com.nwar.individual.clean_architecture_study1.localData.getDummyData
import com.nwar.individual.clean_architecture_study1.presenter.viewModel.MainViewModel
import org.json.JSONObject
import org.junit.Test

import org.junit.Assert.*
import javax.inject.Inject

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

    @Test
    fun getData_isCorrect(){
        val viewModel = InjectViewModel().getViewModelFactory().create(MainViewModel::class.java)
        assertEquals(viewModel.getUserData(), User(0,"user1",17))
    }
}

class InjectViewModel {
    @Inject
    lateinit var viewModel: MainViewModel
    fun getViewModelFactory() = DaggerMainComponent.builder().mainModule(MainModule()).build().testInject()
}