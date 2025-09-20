package chapter4.dataBasedDesign;

public enum MovieType {
    // 할인 정책의 종류를 결정해줌
    // 예매 가격 계산을 위해서는 Movie에 할당된 정책이 무엇인지를 알아야하는데,
    // MovieType의 인스턴스를 속성으로 포함시켜서 결정함.
    AMOUNT_DISCOUNT,
    PERCENT_DISCOUNT,
    NONE_DISCOUNT
}
