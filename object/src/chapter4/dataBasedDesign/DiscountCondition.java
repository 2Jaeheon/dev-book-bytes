    package chapter4.dataBasedDesign;

    import java.time.DayOfWeek;
    import java.time.LocalTime;

    public class DiscountCondition {

        private DiscountConditionType type;

        private int sequence;

        private DayOfWeek dayOfWeek;
        private LocalTime startTime;
        private LocalTime endTime;

        // 여기서도 마찬가지로 내부에 DiscountConditionType을 포함하고 있다는 정보 노출
        public DiscountConditionType getType() {
            return type;
        }

        // 이렇게 isDiscountable 메서드의 시그니처를 보면 DayOfWeek 타입의 요일 정보와, LocalTime 타입의 시간 정보를 파라미터로 받음.
        // 이는 요일과 시간정보가 인스턴수 변수로 포함됨을 인터페이스를 통해서 외부에 노출하고 있는 것임.
        public boolean isDiscountable(DayOfWeek dayOfWeek, LocalTime time) {
            if (type != DiscountConditionType.PERIOD) {
                throw new IllegalArgumentException();
            }

            return this.dayOfWeek.equals(dayOfWeek) &&
                this.startTime.compareTo(time) <= 0 &&
                this.endTime.compareTo(time) >= 0;
        }

        // 여기서도 마찬가지로 순번 정보가 외부에 노출되고 있음.
        public boolean isDiscountable(int sequence) {
            if (type != DiscountConditionType.SEQUENCE) {
                throw new IllegalArgumentException();
            }

            return this.sequence == sequence;
        }

        /*public void setType(DiscountConditionType type) {
            this.type = type;
        }

        public int getSequence() {
            return sequence;
        }

        public void setSequence(int sequence) {
            this.sequence = sequence;
        }

        public DayOfWeek getDayOfWeek() {
            return dayOfWeek;
        }

        public void setDayOfWeek(DayOfWeek dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
        }

        public LocalTime getStartTime() {
            return startTime;
        }

        public void setStartTime(LocalTime startTime) {
            this.startTime = startTime;
        }

        public LocalTime getEndTime() {
            return endTime;
        }

        public void setEndTime(LocalTime endTime) {
            this.endTime = endTime;
        }*/
    }
