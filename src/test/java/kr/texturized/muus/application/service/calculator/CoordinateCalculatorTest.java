package kr.texturized.muus.application.service.calculator;


import kr.texturized.muus.application.service.exception.OutOfCoordinateRangeException;
import kr.texturized.muus.common.coordinate.CoordinateCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CoordinateCalculatorTest {

    @FunctionalInterface
    static interface QuadFunction<T> {
        void get(T a, T b, T c, T d);
    }

    private final CoordinateCalculator calculator = new CoordinateCalculator();
    @Test
    void calculateCoordinate() {
        double latitude = calculator.meterToLatitude(1_000_000.0);
        double longitude = calculator.meterToLongitude(1_000_000.0);
        System.out.println("latitude = " + latitude);
        System.out.println("longitude = " + longitude);
    }

    @Test
    void invalidRangeThenExpectError() {
        Assertions.assertThrows(OutOfCoordinateRangeException.class, () ->{
            calculator.validateRange(0.0, 0.0, 1, 1);
        });
        Assertions.assertThrows(OutOfCoordinateRangeException.class, () ->{
            calculator.validateRange(33.0, 124.6, 1, 1);
        });
        Assertions.assertThrows(OutOfCoordinateRangeException.class, () ->{
            calculator.validateRange(37.500718, 127.028226, 1_000_000.0, 1_000_000.0);
        });
    }

    @Test
    void validRange() {
        calculator.validateRange(37.500718, 127.028226, 1, 1);
        calculator.validateRange(37.500718, 127.028226, 100_000.0, 100_000.0);
    }
}