# Room

※ SQLite에서는 대소문자를 구분하지 않음.



### 구성요소

+ Database : 데이터베이스 보유자 (Entity 설정, 버전설정)
+ Entity : Database 내의 테이블 (속성, 도메인 설정 가능)
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

@Entity로 정의된 class, 그 class의 Collection, Array를 인자로 받을 수 있음.

Long 혹은 Array< Long >, List< Long >을 반환함. (Insert한 값의 row Id)



### Update

```kotlin
@Dao
interface UpdateDao {
    @Update
    fun update(vararg items : User)
}
```

return 값으로 Int, 변경된 행의 수를 받을 수 있음.



### Delete

```kotlin
@Dao
interface DeleteDao {
    @Delete
    fun delete(vararg items : User)
}
```

return 값으로 Int, 삭제된 행의 수를 받을 수 있음.



### Query

DB 조회를 위함.

```kotlin
@Dao
interface QueryDao {
    @Query("SELECT * FROM USER") 
    fun loadAll() : List<User> // User테이블에서 모든 튜플을 가져와 반환
  
    @Query("SELECT * FROM USER WHERE id > :_id")
    fun loadAllById(_id : Int) : List<IdOnly> 
       // User테이블에서 id값이 인자값을 초과한 튜플을 가져와 반환(쿼리문에 파라미터 사용.)
  
  	@Query("Select * FROM user WHERE id IN (:ids)")
    fun loadFromRegions (ids : List<Int>) : LiveData<List<User>>
   		//개수가 정해지지 않은 파라미터를 사용. LiveData로 자동변환해줌.
  
  	@Query("SELECT * FROM ForeignEntity" +
            "INNER JOIN USER ON USER.id = ForeignEntity.id"
            + "WHERE id > 100")
    fun loadJoin() : List<User>
    // 조인도 가능하다.
}

data class IdOnly(
    val id : Int
) // data class를 새로 정의해 data class 내부의 속성값만 가져올 수 있음.
```



## Database



#### Converter

Room은 기본타입과 wrapping 타입만 지원(Java기준.)하기 때문에, 그 외의 타입을 사용할 때는 Converter를 만들어야 함.

```kotlin
class TypeConverter() {
    @TypeConverter
    fun fromTimeStamp(value : Long?) = value?.let { 
        Date(it)
    }
    
    @TypeConverter
    fun dateToTimeStamp(date : Date?) = date?.time
}
```





[참고자료 : "해리의 유목코딩"  블로그]([https://medium.com/harrythegreat/%EB%B2%88%EC%97%AD-%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-room-7%EA%B0%80%EC%A7%80-%EC%9C%A0%EC%9A%A9%ED%95%9C-%ED%8C%81-18252a941e27](https://medium.com/harrythegreat/번역-안드로이드-room-7가지-유용한-팁-18252a941e27))