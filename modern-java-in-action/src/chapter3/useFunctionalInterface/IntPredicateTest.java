package chapter3.useFunctionalInterface;

public class IntPredicateTest {

    public static void main(String[] args) {
        IntPredicate evenNumbers = (int i) -> i % 2 == 0;
        boolean evenNumberTest = evenNumbers.test(1000);// 참 (박싱 없음)

        Predicate<Integer> oddNumbers = (Integer i) -> i % 2 != 0;
        boolean oddNumberTest = oddNumbers.test(1000);// 거짓(박싱)

        System.out.println(evenNumberTest);
        System.out.println(oddNumberTest);
    }
}
