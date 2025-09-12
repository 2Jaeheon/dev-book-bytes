package chapter2.theater;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 이런식으로 기본적인 알고리즘 흐름을 부모클래스에 구현하고, 중간에 필요한 처리를 자식에게 위임하는 디자인 패턴을 TEMPLATE METHOD 패턴이라 한다.
// 실제 애플리케이션에서는 DiscountPolicy의 인스턴스를 생성할 필요가 없으므로 추상클래스로 구현
public abstract class DiscountPolicy {

    // DiscountPolicy는 여러개의 조건을 가질 수 있다.
    // List로 나타냈음. 즉, 하나의 할인 정책은 여러 개의 할인 조건을 포함 가능
    private List<DiscountCondition> conditions = new ArrayList<>();

    // Movie에서는 discountPolicy만으로 생성자를 통해 초기화
    // DiscountPolicy의 생성자에서는 여러개의 인스턴스를 허용함.
    public DiscountPolicy(DiscountCondition... conditions) {
        this.conditions = Arrays.asList(conditions);
    }

    public Money calculateDiscountAmount(Screening screening) {
        for (DiscountCondition each : conditions) {
            if (each.isSatisfiedBy(screening)) {
                // 하나라도 할인 조건에 만족한다면 추상메서드인 getDiscountAmount를 호출해서 할인 요금을 계산하며,
                return getDiscountAmount(screening);
            }
        }

        // 하나라도 없다면 0을 반환
        return Money.ZERO;
    }

    abstract protected Money getDiscountAmount(Screening screening);
}
