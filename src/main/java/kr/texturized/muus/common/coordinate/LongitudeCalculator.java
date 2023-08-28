package kr.texturized.muus.common.coordinate;

/**
 * Longitude Calculator.
 */
public interface LongitudeCalculator {

    double LONGITUDE_OFFSET = 111_132.92;

    double meterToLongitude(final double meter);

    double longitudeToMeter(final double longitude);

}
