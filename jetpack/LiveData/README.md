# LiveData

LiveData를 공부하기 위해서는, [LifeCycle](../Lifecycle)을 먼저 공부해야 합니다.



### 개요

+ Data holder class.

+ 일반적인 Observable과는 달리, LifeCycle을 따름.

  => 활성 중인 라이프사이클 상태의 Observer에게만 데이터를 발행할 수 있음.

※ 활성 중 : Lifecycle State가 STARTED 혹은 RESUMED상태인 것.

+ LifecycleOwner 인터페이스를 구현하는 객체와 관찰자를 같이 등록하여 LifecycleOwner 인터페이스를 구현한 객체가 DESTROYED 상태가 되었을 때, 구독을 해지한다.

  => LiveData 객체를 안전하게 Observe하며, Memory Leak 등을 신경쓸 필요가 없음.



### 이점

+ UI와 데이터 상태를 일치시킬 수 있음
+ Memory Leak 방지
+ 액티비티가 멈춤으로 인한 크래시 방지
+ 라이프사이클에 따라 수동으로 관리할 필요 없음.
+ 라이프사이클이 비활성화 되었다가 다시 활성화되어도 최신 데이터를 수신함.
+ 화면 회전 등의 구성 변경으로 인해 액티비티 혹은 프래그먼트 재생 시, 사용가능한 최신 데이터 수신.
+ 싱글톤 패턴 활용 => 앱에서 공유 가능

 

### 사용하기

1. 특정 유형의 데이터를 저장할 LiveData 인스턴스 생성.(제네릭 문법을 사용하며, 일반적으로 AAC ViewModel에서 수행.)
2. LiveData 객체의 데이터가 변경될 때 발생시킬 작업 정의하기.(Observer 인터페이스를 이용하며, onChange()를 정의해야 함. 일반적으로 UI controller에서 정의함.)
3. LiveData 객체의 observe() 메서드를 이용해 Observer를 연결함.(LifecycleOwner 정보도 넘겨줘야함.)

```kotlin
class CustomViewModel : ViewModel() {
  val data : MutableLiveData<String> by lazy {
    MutableLiveData<String>()
  } // 1. LiveData 인스턴스 생성
}

class Activity : AppcompatActivity() {
  val textView by lazy {
    findViewById<TextView>(R.id.textview)
  }
  override fun onCreate(savedInstance : Bundle?) {
    ...
    val model 
    = ViewModelProviders.of(this).get(CustomViewModel::class.java)
    
  	val observer = Observer<String> {
    	it -> textView.text = it
  	} // 2. LiveData 객체 데이터 변경시 발생시킬 작업 정의
    
    model.data.observe(this, observer) // 3. LiveData 객체에 Observer 연결
  }
}
```



### LiveData 값 변경

setValue(T) / postValue(T)로 LiveData 내의 값을 변경할 수 있음.

```kotlin
button.setOnClickListener {
  val text = edittext.text.toString()
  model.data.value(text)
}
```

setValue(T) : Main Thread에서 사용

postValue(T) : 다중 스레드 환경일 때, Main Thread가 아닐 때 사용.



### Transform LiveData

LiveData 데이터를 발행하기 전, 데이터를 변경해 보내고 싶을 때에 사용함.

```kotlin
Transformations.map(LiveData<T>()) {
  item -> returnType
}
Transformations.switchMap(LiveData<T>()){
  item -> returnType
}
```

