package kr.texturized.muus.application.service.calculator;


import static org.assertj.core.api.Assertions.*;

import kr.texturized.muus.application.service.exception.OutOfCoordinateRangeException;
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
        expectInvalid(0.0, 0.0, 1, 1);
        expectInvalid(33.0, 124.6, 1, 1);
        expectInvalid(37.500718, 127.028226, 1_000_000.0, 1_000_000.0);
    }

    void expectInvalid(double latitude, double longitude, double widthMeter, double heightMeter) {
        try {
            calculator.validateRange(latitude, longitude, widthMeter, heightMeter);
            fail(String.format(
                "Expected error but it is valid: l: %f, r: %f, w: %f, h: %f%n",
                latitude,
                longitude,
                widthMeter,
                heightMeter
            ));
        } catch(OutOfCoordinateRangeException e) {
        }
    }

    @Test
    void validRange() {
        calculator.validateRange(37.500718, 127.028226, 1, 1);
        calculator.validateRange(37.500718, 127.028226, 100_000.0, 100_000.0);
    }
}