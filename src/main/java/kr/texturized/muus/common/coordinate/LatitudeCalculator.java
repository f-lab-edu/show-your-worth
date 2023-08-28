package kr.texturized.muus.common.coordinate;

/**
 * Latitude Calculator.
 */
public interface LatitudeCalculator {
    double LATITUDE_OFFSET = 111_319.9;

    double meterToLatitude(final double meter);

    double latitudeToMeter(final double latitude);
}
