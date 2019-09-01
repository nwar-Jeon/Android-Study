package com.nwar.individual.clean_architecture_study1.data.dataSource

import com.nwar.individual.clean_architecture_study1.domain.entity.User
import com.nwar.individual.clean_architecture_study1.localData.getDummyData

class MainDataSourceImpl() : MainDataSource {
    override fun getDataFromDummy(): User = getDummyData()
}