# DataSource 구현체의 메서드

### PageKeyDataSource

```kotlin

```



### ItemKeyedDataSource<K,V>

```kotlin
fun getKey(item : V) : K {}
// 파라미터로 받은 아이템의 키값 반환
fun loadAfter(params : LoadParams<K>, callback : LoadCallback<V>) {}
// 추가할 데이터를 로드해 callback의 onResult 호출
fun loadBefore(params : LoadParams<K>, callback : LoadCallback<V>) {}
// 이전의 데이터를 로드해 callback의 onResult 호출
fun loadInitial(params : LoadInitialParams<K>, callback : LoadInitialCallback<V>) {}
// 최초의 데이터를 로드해 callback의 onResult 호출
```



### PositionalDataSource

```kotlin

```

