# DataBinding



### 개요

+ 선언적 형식으로 레이아웃의 UI 구성요소를 앱의 데이터 소스와 결합.

  => findViewById() 함수를 통해 화면 상의 뷰를 직접 찾을 필요가 없음.

+ Activity에서 많은 UI 프레임워크 호출을 삭제할 수 있음.

+ 앱 성능 향상, 메모리 leak, NPE 방지



+ Android 4.0 이상 지원
+ Android Plugin for Gradle ver 1.5.0 이상 지원



### DataBinding 허용하기

```java
// Module:app

android {
  ...
  dataBinding {
    enabled = true
  }
  ...
}
```



### New DataBinding compiler

+ 기존의 데이터바인딩 컴파일러는 바인딩 클래스를 컴파일하는 과정에서 생성.

+ 코드를 컴파일하지 못하면 에러가 발생.

  => 위의 문제를 방지

```properties
#gradle.properties
android.databinding.enableV2=true
```



### 레이아웃과 바인딩하기

+ Activity

```kotlin
val binding : XXXBinding = DataBindingUtil.setContentView(this,R.layout.XXX)
```

+ Fragment

```kotlin
val binding : XXXBinding = XXXBinding.inflate(layoutInflater)
```

+ View/ViewHolder

```kotlin
class ViewHolder(val view : View) : RecyclerView.ViewHolder(view){
	val binding : XXXBinding = DataBindingUtil.bind(view)
}
```





### 레이아웃 및 바인딩 표현식

표현 언어를 사용해 뷰에 의해 발송된 이벤트를 처리하는 표현식 사용.

DataBinding은 레이아웃의 View를 데이터 객체와 바인딩하는 클래스를 자동으로 생성.



layout을 루트 태그로 사용하며, 데이터 요소(아래 코드에서 설명)과 레이아웃의 루트 태그로 시작한다.

+ xml 정의

```xml
<!-- activity_main.xml -->

<layout xmlns:android="http://schmas.android.com/...">
  <data>
    <variable name="name" type="packageroot.ClassName"/>
  </data> 
  <!-- ▲ 데이터 요소. name속성을 통해 변수이름을 지정하고 type을 통해 클래스 지정.-->
  <LinearLayout>
    ...
    <TextView
              ...
              android:text=@{name.examName, default="null"}/>
<!-- 위에서 설정한 변수 이름을 통해 객체 내부의 변수에 접근. 값이 없으면 null로 설정.-->
  </LinearLayout>
</layout>
```

+ data가 정의된 클래스

```kotlin
// ClassName.kt
package packageroot

data class ClassName(val examName : String)
```

+ 데이터를 레이아웃과 바인딩하기

```kotlin
// MainActivity.kt
override fun onCreate(savedInstance : Bundle){
  super.onCreate(savedInstance)
  val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
  binding.user = User("Name!")
}
```



### 표현식

+ 수학 기호 : + - / * %
+ String 결합 : +
+ 논리 연산 : && ||
+ 비트 연산 : & | ^
+ 단항 연산 : + - ! ~
+ 쉬프트 연산 : >> >>> <<
+ 비교 연산 : == > < >= <=
+ 타입 확인 : instanceof
+ grouping : ( )
+ Literal : Character, String, numeric, null
+ 형변환
+ 메서드 호출
+ 필드 접근
+ 배열 접근 : []
+ 3항 연산 : ? :
+ 엘비스 연산자 : ?? (왼쪽 값이 null일 경우 오른쪽 값을 선택.)

##### 예시

```xml
android:text="@{String.valueOf(index + 1)}"
android:visivility="@{age > 13 ? View.GONE : View.VISIBLE}"
```

+ this
+ super
+ new
+ 위 세 개의 연산자는 지원되지 않음.



Data Object의 필드를 ObservableField로 선언하면 바인딩한 뷰가 값이 바뀌는 것을 감지하여 뷰의 상태를 바꿀 수 있음.



### 이벤트 핸들링

+ 메서드 레퍼런스 이용

```XML
<data>
  <variable name="handler" type="..."/>
</data>
...
android:onClick="@{handler::onClick}"
```

+ 람다식 이용 (인자값 필요할 때 사용)

```xml
<data>
  <variable name="handler" type="..."/>
  <variable name="obj" type="..."/>
</data>
...
android:onClick="@{(view) -> handler.onClick(obj)}"
```



### import

```xml
<data>
  <import type="android.view.View"/>
</data>
...
...="@{XXX ? View.VISIBLE : View.GONE}"
```



##### 타입 별칭

```xml
<data>
  <import type="..." alias="ASDF"/>
</data>
```

+ type에 명시한 클래스를 alias속성으로 설정한 이름으로 사용할 수 있음.



### Generic in xml

```xml
<data>
  <variable name="name" type="java.util.List&it;String>"/>
  <variable name="name2" type="java.util.Map$it;String, String>"/>
</data>
```

xml 데이터 요소로 컬렉션 등을 사용할 때, 제네릭 타입이 필요할 때 사용.

```xml
<!-- 제네릭 타입을 명시할 때, 아래와 같이 사용 -->
&it;T,V>
&it;String>
...
```



## Observable field

DataBinding으로 바인딩한 데이터의 변화를 감지해 뷰의 상태를 변경할 수 있는데, 이를 위해 사용하는 필드의 타입.

ObservableField클래스를 사용하며, 제네릭을 이용해 타입을 지정한다.

기초타입은 모두 ObservableInt 등의 클래스가 제공되어있음.

```kotlin
class Class(
  val item1 = ObservableInt()
  val item2 = ObservableLong()
  val item3 = ObservableField<String>()
  val item4 = ObservableField<CustomClass>()
)
```

+ set/get

```kotlin
val class1 = Class()
class1.item1 = 5
class1.item2 = 10L
class1.item3 = "Google"
class1.item4 = CustomClass()
```

### Observable Collection

+ ObservableArrayMap

  데이터 요소에 추가한 후, key를 통해 값에 접근.

  ```xml
  <data>
    <import type="android.databinding.ObservableMap"/>
    <variable name="map" type="ObservableMap&it;String, Object>"/>
  </data>
  ...
  android:text = "@{map.key}"
  ```

+ ObservableArrayList

데이터 요소에 추가한 후, 인덱스를 통해 값에 접근

```xml
<data>
  <import type="android.databinding.ObservableList"/>
  <variable name="list" type="ObservableList&it;String>"/>
</data>
...
android:text="@{list[index]}"
```



### Observable Object 만들기

수신 등록 메커니즘을 구현하는 클래스인 BaseObservable 클래스를 제공.

이 클래스를 구현하는 데이터 클래스는 속성이 변경될 때 이를 통지하는 역할을 함.

```kotlin
class CustomObject : BaseObservable(){
  @get:Bindable
  var field1 : String = ""
  	set(value) {
      field = value
      notifyPropertyChanged(BR.field1)
    }
  ...
}
```

데이터 바인딩은 리소스 ID를 포함하는 모듈 패키지에 BR 클래스를 생성한다.

데이터 클래스의 기본 클래스를 변경할 수 없는 경우, PropertyChangeRegistry개체를 통해 수신자에게 효율적으로 등록, 통지 가능.



### 2-Way binding

+ Model의 변화를 UI(View)에게도 전달하지만,

+ UI(View)의 변화를 Model에 전달하기도 하는 것. (InverseBinding)

```kotlin
//...Activity.class
binding.setModel(Model())

//Model.class
class Model(){
  val text = ObservableField<String>()
}
```



```xml
<layout>
	<data>
    <variable>
      name="model" type="...Model"
    </variable>
  </data>
	<EditText
  	        android:text="@={model.text}"/>
	<!-- 2-way binding에서는 @=를 사용. -->
</layout>
```



```kotlin
model.text.set("")
```

위의 text 설정 코드를 통해 model의 데이터를 바꿔 edittext의 text를 바꿀 수 있지만,

2-way binding을 설정했기 때문에 edittext에 텍스트를 입력하면, model의 text의 값이 바뀜.

model의 데이터를 view가 받아오기도 하고, view에서 바뀐 데이터를 model에 반영하기도 하기 때문에

binding, inversebinding 양방향으로 바인딩하기 때문에 2-way binding이라고 부름.



```java
@BindingAdapter("android:text")
public static void setText(TextView textview, String text) {
  textView.setVext(text);
}

@InverseBindingAdapter(attribute = "android:text")
public static String getText(TextView textview) {
  return textView.getText().toString();
}
```

