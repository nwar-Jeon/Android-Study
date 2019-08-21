# AAC-ViewModel

### 개요

+ 시스템에 의해 UI Controller가 파괴되거나, 재생하면 UI컨트롤러에 저장되어있던 일시적인 UI 관련 데이터 손실 가능성.

  => UIController가 재생되면, 데이터를 다시 가져와야 한다. 기존의 방법의 경우, 소량의 데이터를 복구하는데에 적합하다. - 많은 데이터를 복구할 수 있는 방법이 필요하다.

+ UI Controller가 비동기식 호출을 할 때, 잠재적 메모리릭을 방지하기 위한 관리를 한다. 화면 회전과 같은 이유로 UI Controller가 다시 생성되는 경우, 이미 호출된 것을 다시 호출해야 하므로, 자원의 낭비.

+ UI Controller에게 과도한 책임을 할당하지 않도록 한다. 

  => 테스트가 용이해짐



### 사용하기

1. ViewModel 상속

```kotlin
class CustomViewModel : ViewModel() { // ViewModel은 추상클래스.
  ...
}
```

2. ViewModel 싱글톤 생성

```kotlin
class Activity : AppCompatActivity() {
  override fun onCreate(savedInstance : Bundle?) {
    val viewModel = ViewModelProviders.of(this)[CustomViewModel::class.java]
  }
}
```



### ViewModel의 생명주기

![AAC_ViewModel](./img/viewmodel-lifecycle.png)

