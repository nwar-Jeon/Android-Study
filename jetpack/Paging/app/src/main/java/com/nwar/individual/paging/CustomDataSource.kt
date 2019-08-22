package com.nwar.individual.paging

import androidx.paging.DataSource
import androidx.paging.ItemKeyedDataSource

class CustomDataSource<K,V>() : ItemKeyedDataSource<K,V>(){

    companion object{
        class Factory<K,V>() : DataSource.Factory<K,V>() {
            override fun create(): DataSource<K, V> = CustomDataSource<K,V>()
        }
    }

    override fun getKey(item: V): K {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadAfter(params: LoadParams<K>, callback: LoadCallback<V>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadBefore(params: LoadParams<K>, callback: LoadCallback<V>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadInitial(params: LoadInitialParams<K>, callback: LoadInitialCallback<V>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

data class Data(val arrayList : ArrayList<String> = arrayListOf()){
    init {
        for (i in 1..100){
            arrayList.add(i.toString() + "번째 아이템")
        }
    }

    fun ArrayList<String>.copyRange(start : Int, count : Int) : List<String>{

    }

    fun getDataRangeAfter(range : Int){

    }
    val count = 0
}