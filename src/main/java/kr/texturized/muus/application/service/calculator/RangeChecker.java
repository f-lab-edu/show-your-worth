package kr.texturized.muus.application.service.calculator;

/**
 * Checker for validating range.
 */
public interface RangeChecker {

    double MIN_LATITUDE = 33.0;
    double MAX_LATITUDE = 38.6;
    double MIN_LONGITUDE = 124.6;
    double MAX_LONGITUDE = 132.0;


    public void validateRange(
        final double latitude,
        final double longitude,
        final double widthMeter,
        final double heightMeter
    );
}
