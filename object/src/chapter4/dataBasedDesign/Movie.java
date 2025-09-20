package chapter4.dataBasedDesign;

import chapter2.theater.Money;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

public class Movie {

    // 할인 조건의 목록이 Movie 안에 있고, 할인 금액과 비율을 Movie 안에 직접 정의하였다는 것이 가장 큰 차이
    private String title;
    private Duration duration;
    private Money fee;
    private List<DiscountCondition> discountConditions;

    private MovieType movieType;
    private Money discountAmount;
    private double discountPercent;

    public MovieType getMovieType() {
        return movieType;
    }

    /*public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public Money getFee() {
        return fee;
    }

    public void setFee(Money fee) {
        this.fee = fee;
    }

    public List<DiscountCondition> getDiscountConditions() {
        return Collections.unmodifiableList(discountConditions);
    }

    public void setDiscountConditions(List<DiscountCondition> discountConditions) {
        this.discountConditions = discountConditions;
    }

    public Money getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Money discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }*/


    // Movie 는 현재 할인 정책의 종류를 (내부 구현을) 외부에 노출중임
    // 할인 정책에 금액 할인 정책, 비율 할인 정책, 미적용 이 세가지가 존재함을 노출
    public Money calculateAmountDiscountedFee() {
        if (movieType != MovieType.AMOUNT_DISCOUNT) {
            throw new IllegalArgumentException();
        }

        return fee.minus(discountAmount);
    }

    public Money calculatePercentDiscountedFee() {
        if (movieType != MovieType.PERCENT_DISCOUNT) {
            throw new IllegalArgumentException();
        }

        return fee.minus(fee.times(discountPercent));
    }

    public Money calculateNoneDiscountedFee() {
        if (movieType != MovieType.NONE_DISCOUNT) {
            throw new IllegalArgumentException();
        }

        return fee;
    }

    // 캡슐화 위반으로 DiscountCondition의 내부 구현이 외부로 노출 -> 결합도가 높음
    // 구현이 변경되는 경우 다른 객체에게 영향이 전파될 확률이 높음
    // 그렇다면 DiscountCondition의 어떤 변경이 Movie에게 미치는 지 파악해야함.

    // PERIOD -> 다른 것으로 변경
    // DiscountCondition이 추가되거나 삭제되면 if else 수정해야 함.
    // 만족 여부 판단 정보가 변경되면 파라미터를 변경해야함.
    // => 위 요소들이 DiscountCondition의 구현임 -> 구현을 변경하면 Movie도 변경해야함.
    public boolean isDiscountable(LocalDateTime whenScreened, int sequence) {
        for (DiscountCondition condition : discountConditions) {
            if (condition.getType() == DiscountConditionType.PERIOD) {
                if (condition.isDiscountable(whenScreened.getDayOfWeek(),
                    whenScreened.toLocalTime())) {
                    return true;
                }
            } else {
                if (condition.isDiscountable(sequence)) {
                    return true;
                }
            }
        }
        return false;
    }
}
