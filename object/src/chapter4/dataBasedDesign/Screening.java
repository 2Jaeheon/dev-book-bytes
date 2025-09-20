package chapter4.dataBasedDesign;

import chapter2.theater.Money;
import java.time.LocalDateTime;

public class Screening {

    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;

    /*public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public LocalDateTime getWhenScreened() {
        return whenScreened;
    }

    public void setWhenScreened(LocalDateTime whenScreened) {
        this.whenScreened = whenScreened;
    }*/

    public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    // 할인 여부를 판단하는데 필요한 정보가 변경되면 Movie의 Disocuntable 메서드로 전달하는 파라미터 종류가 바뀌며,
    // 이는 곧 Screening에서도 변경해야함을 의미한다.
    public Money calculateFee(int audienceCount) {
        // Movie가 금액할인정책 또는 비율할인 정책을 지원하는 경우
        // isDiscountable을 통해서 할인이 가능한지의 여부를 확인한 수 요금을 계산한다.
        // 만일 할인이 안 되거나 정책이 적용되지 않으면 기본으로 None 요금으로 으로 게산한다.
        switch (movie.getMovieType()) {
            case AMOUNT_DISCOUNT:
                if (movie.isDiscountable(whenScreened, sequence)) {
                    return movie.calculateAmountDiscountedFee().times(audienceCount);
                }
                break;
            case PERCENT_DISCOUNT:
                if (movie.isDiscountable(whenScreened, sequence)) {
                    return movie.calculatePercentDiscountedFee().times(audienceCount);
                }
                break;
            case NONE_DISCOUNT:
                return movie.calculateNoneDiscountedFee().times(audienceCount);
        }

        return movie.calculateNoneDiscountedFee().times(audienceCount);
    }
}
