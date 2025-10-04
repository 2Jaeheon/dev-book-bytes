package chapter3.useFunctionalInterface;
@FunctionalInterface
interface Predicate<T> {

    boolean test(T t);
}
