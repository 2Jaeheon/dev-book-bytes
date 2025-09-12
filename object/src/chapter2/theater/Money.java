package chapter2.theater;

import java.math.BigDecimal;

public class Money {

    // 기존에는 금액을 Long타입으로 표현하였지만, 이는 금액과 관련되어 있다는 의미 전달 X
    // 또한 금액과 관련된 로직이 다른 곳에서 중복되어서 구현되는 것 막지 못함
    // 의미를 명시적이고 분명히 표현가능하다면 객체를 사용해서 구현
    public static final Money ZERO = Money.wons(0);

    private final BigDecimal amount;

    public static Money wons(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money wons(double amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    Money(BigDecimal amount) {
        this.amount = amount;
    }

    public Money plus(Money amount) {
        return new Money(this.amount.add(amount.amount));
    }

    public Money minus(Money amount) {
        return new Money(this.amount.subtract(amount.amount));
    }

    public Money times(double percent) {
        return new Money(this.amount.multiply(
            BigDecimal.valueOf(percent)
        ));
    }

    public boolean isLessThan(Money other) {
        return amount.compareTo(other.amount) < 0;
    }

    public boolean isGreaterThanOrEqual(Money other) {
        return amount.compareTo(other.amount) >= 0;
    }
}
