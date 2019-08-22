# Paging

### 개요

+ 앱이 데이터 소스로부터 필요한 정보를 점진적으로 읽어오는 작업을 쉽게 만드는 라이브러리.
+ 이전의 무한 스크롤 기법에 비해 성능에 부담이 적음.
+ 불필요한 네트워크 트래픽, 사용자의 불필요한 데이터사용, 앱 속도 저하를 방지.



=>

+ 지연 로딩
+ 청크 단위 로딩
+ 필요 시 데이터 로딩(On Demand)
+ 백그라운드 스레드 지원



### 사용하기

+ Data Source 클래스를 이용해 페이징된 데이터를 얻어올 데이터소스 정의.
+ PagedList 클래스를 이용해 DataSource로부터 데이터를 불러오기.
+ PagedListAdapter 클래스를 이용해 PagedList의 데이터를 UI에 나타내기. (DiffUtil)



##### DataSource 선택한 후, 구현하기

+ 데이터를 어디서 어떻게 가져올 지 정의함.
+ 3가지 인터페이스 중, 하나를 선택.
  + PageKeyDataSource : 데이터가 다음, 이전 키를 포함할 때 사용
  + ItemKeyedDataSource<K,V> : 정렬된 데이터에서 사용
  + PositionalDataSource : 특정 위치의 데이터를 가져올 때 사용
    + [메서드 참고](./DataSource_구현체의_메서드.md)



##### PagedList 생성

+ 데이터 갱신 관찰을 정의
+ Builder를 통해 PagedList를 얻고, 2가지 PagedList.Builder 중 하나를 선택.
  + LivePagedListBuilder : LiveData를 이용해 데이터 관찰.
  + RxPagedListBuilder : RxJava2를 이용해 데이터 관찰. Flowable / Observable.

+ 생성 시, DataSource.Factory 인스턴스를 넘겨야 함.