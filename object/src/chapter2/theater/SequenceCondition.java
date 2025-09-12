package chapter2.theater;

public class SequenceCondition implements DiscountCondition {

    private int sequence;

    public SequenceCondition(int sequence) {
        this.sequence = sequence;
    }

    // 상영 순번과 일치한 경우에는 할인이 가능하기 때문에 true, 아니면 false
    public boolean isSatisfiedBy(Screening screening) {
        return screening.isSequence(sequence);
    }
}
