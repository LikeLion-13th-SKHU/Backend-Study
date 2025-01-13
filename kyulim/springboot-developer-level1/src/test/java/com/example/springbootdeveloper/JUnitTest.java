package com.example.springbootdeveloper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JUnitTest {
    @DisplayName("1 + 2 = 3이다") // 테스트 이름
    @Test // 테스트 메서드
    public void junitTest() {
        int a = 1;
        int b = 2;
        int sum = 3;

        Assertions.assertEquals(sum, a + b); // 값이 같은지 확인
    }

//    @DisplayName("1 + 3 = 4이다") // 테스트 이름
//    @Test // 테스트 메서드
//    public void junitFailedTest() {
//        int a = 1;
//        int b = 3;
//        int sum = 3;
//
//        Assertions.assertEquals(sum, a + b); // 실패하는 케이스
//    }

    @Test
    public void junitQuiz1() {
        String name1 = "홍길동";
        String name2 = "홍길동";
        String name3 = "홍길동";

        // 모든 변수가 null이 아닌지 확인
        assertThat(name1).isNotNull();
        assertThat(name2).isNotNull();
        assertThat(name3).isNotNull();

        // name1과 name2가 같은지 확인
        assertThat(name1).isEqualTo(name2);

        // name1과 name3이 다른지 확인
        assertThat(name1).isNotEqualTo(name3);
    }

    @Test
    public void junitQuiz2() {
        int number1 = 15;
        int number2 = 0;
        int number3 = -5;

        // number1은 양수인지 확인
        assertThat(number1).isPositive();

        // number2는 0인지 확인
        assertThat(number2).isZero();

        // number3은 음수인지 확인
        assertThat(number3).isNegative();

        // number1은 number2보다 큰지 확인
        assertThat(number1).isGreaterThan(number2);

        // number3은 number2보다 작은지 확인
        assertThat(number3).isLessThan(number2);
    }
}
