# Room

※ SQLite에서는 대소문자를 구분하지 않음.



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
@Entity(tableName = "table", primaryKeys = arrayOf("id", "name"))
// 테이블 이름을 table으로 지정하고, 복합 PK를 사용.
data class User(
  @ColumnInfo(name = "id")
  val _id : Int, // 이 속성의 DB에서의 이름을 id로 지정
  @ColumnInfo(name = "name")
  val _name : String, // 이 속성의 DB에서의 이름을 name으로 지정. id와 name이 기본키.
  @Ignore // 이 속성은 테이블 구성에서 제외됨.
  val picture : Bitmap
)

@Entity(foreignKeys = arrayOf( // 외래키 설정. (Array<ForeignKey>)
    ForeignKey(
        entity = SecondEntity::class, // 참조할 테이블 설정
        parentColumns = arrayOf("id"), // 참조할 테이블의 id 속성과
        childColumns = arrayOf("foreign_id") // 아래 정의된 foreign_id 속성 연결.
    )
))
data class ForeignEntity(
    val foreign_id : Int,
    val name : String
)
```



## DAO(Data Access Object)

+ 추상클래스 혹은 인터페이스로 구현.
+ RoomDatabase를 인자로 받는 생성자를 만들어야 추상클래스로 구현 가능.
+ Room은 Query과정을 main 스레드에서 하지 않음.



### Insert

```kotlin
interface InsertDao {
    @Insert
    fun insertItems(vararg items : User)

    @Insert
    fun insertTwoItem(item1 : User, item2 : User)

    @Insert
    fun insertItemAndItemList(item : User, items : List<User>)
}
```

