# 동작 파라미터로 코드 전달하기

동작 파라미터화를 이용하면 자주 바뀌는 요구사항에 효과적으로 대응할 수 있다.

동작 파라미터(behavior parameterization)는 아직 어떻게 실행할 것인지 결정하지 않은 코드블럭을 말한다.

이 코드 블럭은 나중에 프로그램에서 호출한다. 즉, 코드 블럭의 실행은 나중으로 미뤄진다. 예를 들어서 나중에 실행될 메서드의 인수로 코드 블록을 전달할 수 있다.

만약 예를 들어서 다음과 같은 리스트의 모든 요소에 대해서 “어떤 동작”을 수행해야 한다고 하자.

```java
public static List<Apple> filterGreenApples(List<Apple> inventory) {
	List<Apple> list = new ArrayList<>();
	for(Apple apple : inventory) {
		if("green".equals(apple.getColor()) {
			result.add(apple);
		}
	}
	return result;
}
```

위의 로직은 녹색 사과만 필터링하는 메서드이다. 근데, 빨간 사과가 추가되고, 무게가 150g 이상인 사과만 필터링하고, 유기농 사과인지 필터링하고 하는 듯한 식으로 요구사항이 바뀐다면??

→ 메서드 파라미터를 계속 추가할 수 있지만, 불편함

⇒ 동작 파라미터로 유연성을 확보할 수 있음

# 동작 파라미터화

사과의 어떤 속성을 기초로 해서 불리언 값을 반환하도록 하는 방법을 사용하면 된다. 참 또는 거짓을 반환하는 함수를 프레디케이트라고 한다.

```java
public interface ApplePredicate {
	boolean test(Apple apple);
}
```

이제 위 Predicate를 통해서 다양한 선택 조건을 대표하는 여러 버전의 ApplePredicate를 정의할 수 있다.

```java
public class AppleHeavyWeightPredicate implements ApplePredicate {
	public boolean test(Apple apple) {
		return apple.getWeight() >= 150;
	}
}

public class AppleGreenColorPredicate implements ApplePredicate {
	public boolean test(Apple apple) {
		return "green".equals(apple.getColor());
	}
}
```

이를 전략(strategy pattern)패턴이라고 한다.

어떤 알고리즘(전략)을 실행할지 런타임에 선택할 수 있도록 하는 디자인 패턴

알고리즘을 인터페이스로 캡슐화해서, 상황에 따라 다른 구현체를 주입

그러면 기존의 filterApples() 메서드에서 컬렉션을 반복하는 로직과 컬렉션의 각 요소에 적용할 동작을 분리할 수 있다.

```java
public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
	List<Apple> list = new ArrayList<>();
	for(Apple apple : inventory) {
			if(p.test(apple) {
				list.add(apple);
			}
	}
	return result;
}
```

그러면 이제 요구사항 추가 시 ApplePredicate를 적절히 구현하는 클래스를 만들면 됨.

# 복잡한 과정을 간소화

근데 새로운 동작을 전달하려면 ApplePredicate를 구현하는 클래스들을 만들고 인스턴스화 해야한다. 이는 매우 번거로운 작업임.

⇒ 익명 클래스를 사용하자. (추후 람다 표현식으로 업데이트)

```java
List<Apple> redApples = filterApples(inventory, new ApplePredicate(){
	public boolean test(Apple apple) {
		return "red".equals(apple.getColor());
	}
});
```

# 더 간소화하자.

```java
List<Apple> result = filterApples(inventory, 
	(Apple apple) -> "red".equals(apple.getColor());
```

# 리스트 형식으로 추상화

```java
public interface Predicate<T> {
	boolean test(T t);
}

public static <T> List<T> filter(List<T> list, Predicate<T> p) {
	List<T> result = new ArrayList<>();
	for(T e : list) {
		if(P.test(e)) {
			result.add(e);
		}
	}
	return result;
}
```

```java
List<Apple> redApples = filter(inventory, 
	(Apple apple) -> RED.equals(apple.getColor()));

List<Integer> evenNumbers = filter(numbers, (Integer i) -> i % 2 == 0);
```

# 실전 예제

정렬을 한다고 해보자. 무게를 중심으로 정렬하다가 색으로 정렬하고 싶어질 수 있다. 따라서 개발자는 요구사항에 발맞춰 정렬할 수 있도록 구축해야 한다.

```java
inventory.sort(new Comparator<Apple>() {
	public int compare(Apple a1, Apple a2) {
		return a1.getWeight().compareTo(a2.getWeight());
	}
);

// 더 쉽게 구현해보자.
inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
```

# Runnable로 코드 블록 실행하기

어떤 코드를 사용할지 Thread에게 알려줄 수 있다. 자바 8까지는 void run 메서드를 포함한 Runnable인터페이스 구현이 일반적이었다.

자바 8 이후로는 ExecutorService를 이용하여 테스크를 스레드풀로 보내고 결과를 future로 저장할 수 있다. Callable인터페이스를 이용해서 결과를 반환하는 테스크를 만든다.

```java
public interface Callable<V> {
	V.call();
}
```

# 후기

기존에는 메서드를 인자로 보낸다는 내용이 전혀 이해가 가지 않았는데, 컴파일러를 배운 뒤여서 그런지 상대적으로 머릿속에 잘 들어올 수 있었다.

신기하게 수업을 따라가기에 급급하고 학점 따기에 바빠서 다른 프로젝트들을 개인적으로 진행하지도 못했고, 아쉬웠던 점이 많았는데, 점점 동일한 내용을 접할 때 이해의 폭이 많이 넓어졌다는 것을 체감하는 순간 성장했음을 느끼고는 한다.

여전히 많이 부족하다고 생각하지만, 이전에 왜 이런 내용을 이해하지 못했지? 하는 생각이 들 정도이다.

동작 파라미터를 통해서 메서드를 보낸다는 것이 함수형 프로그래밍의 일부라는 것은 알고 있었지만, 실제로 어떻게 동작하는지도 몰랐고, 무엇인지 정확하게 알지 못했다.

그렇지만 코딩테스트를 직접 풀어보면서 Comparator를 만들어서 익명객체를 넘기는 것과, lambda를 사용했을 때의 감각 덕분인지 런타임 시점에 처리할 수 있다는 것이 참 좋다는 생각을 했다.

오늘 작성된 코드는 내일 레거시가 된다고 하지 않는가. 어쩌면 가장 좋은 코드란 요구사항의 변화를 가장 수월하게 수용가능한 코드가 아닐까 하는 생각이 든다.