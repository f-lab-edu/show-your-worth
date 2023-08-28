package kr.texturized.muus.application.service.calculator;


import kr.texturized.muus.application.service.exception.OutOfCoordinateRangeException;
import kr.texturized.muus.common.coordinate.CoordinateCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CoordinateCalculatorTest {

    private final CoordinateCalculator calculator = new CoordinateCalculator();

    /**
     * This test is for calculation of latitude/longitude for each meter.
     * Let it leave for easy caculation.
     */
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