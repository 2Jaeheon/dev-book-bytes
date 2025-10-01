package chapter5.responsibility;

public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}
