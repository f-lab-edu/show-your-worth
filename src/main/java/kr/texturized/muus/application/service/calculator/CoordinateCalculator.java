package kr.texturized.muus.application.service.calculator;

import org.springframework.stereotype.Component;

/**
 * Coordinate Calculator.
 * <br>
 * ref. <a href="https://chat.openai.com/share/f45d836e-cf9b-4b08-af2b-f3c73a26d141">ChatGPT</a>
 */
@Component
public class CoordinateCalculator implements LatitudeCalculator, LongitudeCalculator {

    private static final double LATITUDE_OFFSET = 111_319.9;
    private static final double LONGITUDE_OFFSET = 111_132.92;

    @Override
    public double meterToLatitude(double meter) {
        return meter / LATITUDE_OFFSET;
    }

    @Override
    public double latitudeToMeter(double latitude) {
        return latitude * LATITUDE_OFFSET;
    }

    @Override
    public double meterToLongitude(double meter) {
        return meter / LONGITUDE_OFFSET;
    }

    @Override
    public double longitudeToMeter(double longitude) {
        return longitude * LONGITUDE_OFFSET;
    }
}
