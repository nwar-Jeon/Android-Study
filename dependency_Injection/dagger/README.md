# Dagger

### 개요

Dependency Injection(의존성 주입)을 위한 Framework.



### 의존성 주입?

외부에서 의존 객체. 즉 의존성을 가진 객체를 생성해 넘겨주는(설정자 혹은 생성자 등)것을 의미한다.

![dagger](D:/Github/Android/Android-Study/dependency_Injection/dagger/img/dagger_image.jpeg)

의존성 주입을 위해서, 객체를 생성하고 넘겨주는 외부의 무언가가 필요함.

이 외부에서 넘겨주는 일을 DI Framework가 하는 일.

Dagger에서는 이를 Component, Modelu이라고 부름.

의존성이 있는 객체의 제어를 외부 Framework로 올리며 Ioc(Inversion of Control) 개념을 구현.

![IOC_Container](D:/Github/Android/Android-Study/dependency_Injection/dagger/img/ioc_image.jpeg)



### Why DI?

의존성 파라미터를 생성자에 작성할 필요가 없음.

Interface에 구현체를 교체하며 상황에 따른 메서드 본체를 바꿀 수 있음. 테스트에 용이.



## Dagger 구성요소

1. Inject
2. Component
   - Subcomponent
3. Module
4. Provider
5. Scope



### Inject

Inject 어노테이션으로 의존성 주입을 요청. 연결된 Component가 Module로부터 객체를 생성해 넘겨줌.



### Component

연결된 Module을 이용해 의존성 객체 생성, Inject로 요청받은 인스턴스에 생성한 객체를 주입.

=> Inject가 의존성 주입을 요청하면, Module을 통해 객체생성, Inject를 요청한 인스턴스에 객체 주입.



#### Subcomponent

Component의 계층 관계. 내부클래스 방식의 하위계층 Component.

Dagger의 주요 개념인 그래프를 형성. Subcomponent에서 먼저 의존성을 검색한 후, 부모로 올라가며 검색.



### Module

Component에 연결되어 의존성 객체 생성.

이후 Scope에 따라 관리도 할 수 있음.



### Provider

객체를 제공하기 위한 메서드에 사용하는 어노테이션.



### Scope

생성된 객체의 생명주기 범위.

안드로이드에서는 주로 Activity, Fragment 등의 생명주기와 맞춰 사용.



## 사용하기

1. 의존성 설정하기
2. Module과 객체를 생성하는 메서드, Provider 구현하기
3. Component interface 만들기. (최소 한 개의 추상메서드가 있어야 하며, 이 메서드로 의존성 주입이 필요한 객체를 반환하거나, 멤버 파라미터로 의존성 주입을 시킬 객체를 넘겨야 함.)
4. Inject를 통해 객체를 주입할 곳을 알리기.
5. Component를 통해 의존성을 주입하기.(Component interface가 DaggerXXX 이름으로 구현됨.)



### 1. 의존성 설정하기

```gradle
dependencies {
	def dagger_verion = "최신 버전"
	
  api "com.google.dagger:dagger:${dagger_version}"
  annotationProcessor "com.google.dagger:dagger-compiler:${dagger_version}"
  api "com.google.dagger:dagger-android:${dagger_version}"
	api "com.google.dagger:dagger-android-support:${dagger_version}" // if you use the support libraries
	annotationProcessor "com.google.dagger:dagger-android-processor:${dagger_version}"
}
```

최신 버전은 [Dagger Github](https://github.com/google/dagger)에서 확인.



### 2. Module과 Provider 구현하기

```kotlin
@Module
class UserModule {
  @Providers
  fun providerUser() = User("이름", 1000)
}

//User.kt
data class User(
    val id : Int,
    val name : String
) {
    override fun toString() = "$id : $name"
}
```



### 3. Component interface 만들기

Module 설정하기.

멤버 파라미터로 의존성 주입을 시킬 객체를 넘기는 메서드 정의②.

```kotlin
@Component(modules = arrayOf(UserMakerModule::class))
interface UserComponent {
  fun inject(userMaker : UserMaker) : Unit // ②
}
```



### 4. Inject를 통해 주입할 곳 알리기

```kotlin
import javax.inject.Inject

class UserMaker() {
  @Inject
 	var user : User
} // 필드에 @Inject 어노테이션 명시.
```



### 5. Component를 통해 의존성을 주입하기.

Dagger 라이브러리에서 @Component 어노테이션을 명시한 클래스를 DaggerXXX 형태로 구현해 주기 때문에 DaggerXXX를 통해 Component를 호출하면 된다.

```kotlin
println(DaggerUserComponent.make().toString()) // "1000 : 이름"

val userMaker = UserMaker()
DaggerUserComponent.inject(userMaker)
println(userMaker.toString) // "1000 : 이름"
```



[참고 자료]([https://cmcmcmcm.blog/2017/07/27/didependency-injection-%EC%99%80-dagger2/](https://cmcmcmcm.blog/2017/07/27/didependency-injection-와-dagger2/))



## 심화

1. Subcomponent
2. Scope
3. Binds
   - Multibinding
4. Singleton