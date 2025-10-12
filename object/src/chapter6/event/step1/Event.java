package chapter6.event.step1;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

// 특정 일자에 실제로 발생하는 사건
public class Event {

    private String subject;
    private LocalDateTime from;
    private Duration duration;

    public Event(String subject, LocalDateTime from, Duration duration) {
        this.subject = subject;
        this.from = from;
        this.duration = duration;
    }

    // reschedule은 Event의 일정을 인자로 전달된 RecurringSchedule의 조건에 맞게 변경한다.
    // 따라서 메서드를 호출하는 isSatisfied 메서드는 Event가 RecurringSchedule에 설정된 조건을 만족하지 못하면
    // Event의 상태를 조건을 만족시키도록 변경한 후 false를 반환한다.

    // 이러한 문제가 발생하는 거은 처음 구현에서는 reschedule 메서드를 호출하는 과정을 빠져있었지만,
    // 요구사항의 변경으로 인해서 추가된 것임.
    // 즉, 명령과 쿼리가 섞이면 예측하기 어렵다.
    // isSatisfied는 쿼리처럼 보이지만, 내부적으로는 부수 효과를 가지는 메서드임
    public boolean isSatisfied(RecurringSchedule schedule) {
        if (from.getDayOfWeek() != schedule.getDayOfWeek() ||
            !from.toLocalDate().equals(schedule.getFrom()) ||
            !duration.equals(schedule.getDuration())) {
            reschedule(schedule);
            return false;
        }
        return true;
    }


    private void reschedule(RecurringSchedule schedule) {
        // 여기서 from의 상태를 변경시킴.
        from = LocalDateTime.of(from.toLocalDate().plusDays(daysDistance(schedule)),
            schedule.getFrom());
        duration = schedule.getDuration();
    }

    private long daysDistance(RecurringSchedule schedule) {
        return schedule.getDayOfWeek().getValue() - from.getDayOfWeek().getValue();
    }
}
