package chapter2.theater;

import java.time.LocalDateTime;

public class Screening {

    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;

    public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    public LocalDateTime getStartTime() {
        return whenScreened;
    }

    public boolean isSequence(int sequence) {
        return this.sequence == sequence;
    }

    public Money getMovieFee() {
        return movie.getFee();
    }

    // 영화를 예매한 후 예매 정보를 담고 있는 Reservation의 인스턴스 생성해서 반환
    public Reservation reserve(Customer customer, int audienceCount) {
        return new Reservation(cusomer, this, calculateFee(audienceCount), audienceCount);
    }

    // Movie의 calculateFee를 호출함. (이 메서드는 1인당 예매 요금을 반환해줌)
    public Money calculateFee(int audienceCount) {
        // Screening이 Movie의 calculateMovieFee() 메서드를 호출하는 것이 아닌,
        // Screening이 Mvoie에게 calculateMovieFee()라는 메시지를 전하는 것임
        return movie.calculateMovieFee(this).times(audienceCount);
    }
}
