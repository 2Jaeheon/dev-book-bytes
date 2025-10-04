package chapter3.functionalInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        Runnable r1 = () -> System.out.println("Hello World1");

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World2");
            }
        };
        process(r1);
        process(r2);
        process(() -> System.out.println("Hello World3"));

        // 람다 전달
        String oneLines = processFile((BufferedReader br) -> br.readLine());
        String twoLines = processFile((BufferedReader br) -> br.readLine() + br.readLine());
    }

    public static void process(Runnable r) {
        r.run();
    }

    // processFile의 동작을 파라미터화 한 것
    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {

            // 실제 작업을 처리하는 부분 (동작을 실행)
            // 함수형 인터페이스를 이용해서 동작 파라미터화해서 전달
            // BufferedReader -> String 시그니처와 일치하는 함수형 인터페이스를 만들어야한다.
            return p.process(br);
        }
    }

    // 함수형 인터페이스를 통한 동작 전달
    @FunctionalInterface
    interface BufferedReaderProcessor {

        String process(BufferedReader br) throws IOException;
    }
}
