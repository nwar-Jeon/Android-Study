## LifeCycle(Android Jetpack)

+ 액티비티의 상태(lifecycle)에 따른 처리 코드를 쉽게 관리하도록 돕는 라이브러리.

+ 액티비티의 코드들을 옵저버 객체로 분리하도록 해 더욱 유지보수/가독성을 높이기 위함.



+ LifecycleOwner 구현 (액티비티.)
+ Lifecycle (액티비티의 상태를 가지고 있음.)
+ LifecycleObserver 구현
+ Observer 등록



### LifecycleOwner

AppcompatActivity 클래스는 FragmentActivity를 상속받으며, 이는 ComponentActivity를 상속받고, 이는 Activity를 상속함과 동시에 LifecycleOwner를 구현한다.

=> AppcompatActivity는 Activity의 역할을 하면서, LifecycleOwner를 구현하기 때문에 LifecycleOwner를 따로 구현할 필요가 없고, **액티비티에서 lifecycle에 접근할 수 있다.** (접근자 이용)

```java
public class ComponentActivity extends Activity implements ... LifecycleOwner {
  ...
  private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);
  
  @Override
  public Lifecycle getLifecycle() {
      return mLifecycleRegistry;
  }
  ...
}
// ComponentActivity에서 구현한 LifecycleOwner.
```



#### LifecycleRegistry

```java
public class LifecycleRegistry extends Lifecycle { // Lifecycle은 추상클래스.
...
	public LifecycleRegistry(@NonNull LifecycleOwner provider) {
	    mLifecycleOwner = new WeakReference<>(provider);
	    ...
	}
...
}
```

※ WeekReference? : 동작 진행 중, 중지 시에 액티비티의 자원을 GC로 소멸시키기 위함.