package kr.texturized.muus.application.service.calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CoordinateCalculatorTest {

    private final CoordinateCalculator calculator = new CoordinateCalculator();
    @Test
    void calculateCoordinate() {
        double latitude = calculator.meterToLatitude(1.0);
        double longitude = calculator.meterToLongitude(1.0);
        System.out.println("latitude = " + latitude);
        System.out.println("longitude = " + longitude);
    }
}