# Room



### 구성요소

+ Database : 데이터베이스 보유자 (Entity 설정, 버전설정)
+ Entity : Database 내의 테이블 (속성, 도메인 설정)
+ Dao: 데이터베이스에 엑세스하는데 사용되는 메서드 (SQL문 사용/Insert 등 어노테이션 사용)



## Dependency 설정

https://developer.android.com/jetpack/androidx/releases/room

```gradle
dependencies {
    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version" // use kapt for Kotlin

    // optional - RxJava support for Room
    implementation "androidx.room:room-rxjava2:$room_version"

    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation "androidx.room:room-guava:$room_version"

    // optional - Coroutines support for Room
    implementation "androidx.room:room-coroutines:$room_version"

    // Test helpers
    testImplementation "androidx.room:room-testing:$room_version"
}
```



## Entity 구성

Entity 어노테이션을 지정한 data class로 선언한다.

```kotlin
@Entity // User라는 이름의 테이블을 생성할 수 있음.
data class User(
  @PrimaryKey  // id(Int) 속성을 PK로 설정함.
  val id : Int
  
  val name : String // name속성을 일반속성으로 설정함.
)
```



```kotlin
@Entity(tableName = "table")
```

