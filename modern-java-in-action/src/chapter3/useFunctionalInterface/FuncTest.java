package chapter3.useFunctionalInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FuncTest {

    public static void main(String[] args) {

        //String 리스트를 인수로 받아 각 String의 길이를 포함하는 Interger 리스트로 변환.
        List<Integer> l = map(
            Arrays.asList("lambdas", "in", "action"),
            (String s) -> s.length() //Function의 apply를 구현하는 람다.
            // String -> Integer
        );

        System.out.println(l); // 7, 2, 6
    }

    // 제네릭 형식 T를 인수로받아서, 제네릭 형식 R 객체를 반환
    // 여기서는 String 리스트를 인수로 받고, 제네릭 형식 Integer 리스트를 반환
    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();

        for (T t : list) {
            result.add(f.apply(t));
        }

        return result;
    }
}
