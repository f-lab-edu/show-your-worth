package kr.texturized.muus.application.service.calculator;

/**
 * Longitude Calculator.
 */
public interface LongitudeCalculator {

    double LONGITUDE_OFFSET = 111_132.92;

    double meterToLongitude(final double meter);

    double longitudeToMeter(final double longitude);

}
