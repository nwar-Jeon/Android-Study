# Room



### 구성요소

+ Database : 데이터베이스 보유자
+ Entity : Database 내의 테이블
+ Dao: 데이터베이스에 엑세스하는데 사용되는 메서드

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

