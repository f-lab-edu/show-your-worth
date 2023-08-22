package kr.texturized.muus.application.service.calculator;

/**
 * Latitude Calculator.
 */
public interface LatitudeCalculator {
    double meterToLatitude(double meter);

    double latitudeToMeter(double latitude);
}
