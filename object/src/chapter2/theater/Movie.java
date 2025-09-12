package chapter2.theater;

import java.time.Duration;

public class Movie {

    private String title;
    private Duration runningTime;
    private Money fee;
    private DiscountPolicy discountPolicy;

    public Movie(String title, Duration runningTime, Money fee, DiscountPolicy discountPolicy) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountPolicy = discountPolicy;
    }

    public Money getFee() {
        return fee;
    }

    // discountPolicy에 calculateDiscountAmount 메시지를 보내서 할인된 요금을 반환받음
    // Movie는 기본 요금에서 반환된 할인 요금을 차감
    public Money calculateMovieFee(Screening screening) {
        // 이곳에는 비율할인과 금액할인에 관해 어떤 것을 적용했는지는 나타나지 않음.
        // Movie는 단지 할인된 요금만을 반환받고 싶을 뿐임
        // 그렇다면 할인 정책에 관한 책임은 어디에? -> DiscountPolicy에 존재
        return fee.minus(discountPolicy.calculateDiscountAmount(screening));
    }
}
