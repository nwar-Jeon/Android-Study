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



### LifecycleObserver 구현

```kotlin
class Observer : LifecycleObserver {
  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  fun funtionName(){
    ...
  }
}
```

LifecycleObserver를 구현한 클래스에서 @OnLifecycleEvent()를 이용해 액티비티의 상태를 감지해 메서드를 실행한다.

위의 코드에서는 액티비티가 onCreate()상태가 되면, funtionName()을 실행한다.

```java
public abstract class Lifecycle {
  ...
  public enum Event {
    ON_CREATE,
    ON_START,
    ON_RESUME,
    ON_PAUSE,
    ON_STOP,
    ON_DESTROY,
    ON_ANY;
  } // 각 이름에 맞는 상태가 되었을 때, @OnLifecycleEvent() 어노테이션으로 실행할 메서드를 지정        할 수 있음.
  
  public enum State {
    DESTROYED,
    INITIALIZED,
    CREATED,
    STARTED,
    RESUMED;
    
    public boolean isAtLeast(@NonNull State state) { return compareTo(state) >= 0; }
  }
}
```





![](./img/lifecycle_state.svg)



동적으로 액티비티의 상태를 알 필요가 있을 때, Lifecycle.State의 값, isAtLeast메서드를 통해 비교할 수 있음.