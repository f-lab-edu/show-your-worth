package kr.texturized.muus.application.service.calculator;

import kr.texturized.muus.application.service.exception.OutOfCoordinateRangeException;
import org.springframework.stereotype.Component;

/**
 * Coordinate Calculator.
 * <br>
 * ref. <a href="https://chat.openai.com/share/f45d836e-cf9b-4b08-af2b-f3c73a26d141">ChatGPT</a>
 */
@Component
public class CoordinateCalculator implements LatitudeCalculator, LongitudeCalculator, RangeChecker {


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

    /**
     * Validate the range for busking.<br>
     * <br>
     * Range of latitude for Korea: 33.0 ~ 38.6<br>
     * Range of longitude for Korea: 124.6 ~ 132.0<br>
     * <br>
     * ref. <a href="https://chat.openai.com/share/f45d836e-cf9b-4b08-af2b-f3c73a26d141">위도/경도 미터환산 공식</a>
     *
     * @param latitude Offset latitude
     * @param longitude Offset longitude
     * @param widthMeter Range of meter(Width)
     * @param heightMeter Range of meter(Height)
     */
    @Override
    public void validateRange(
        final double latitude,
        final double longitude,
        final double widthMeter,
        final double heightMeter
    ) {
        if (latitude < 0.0 || longitude < 0.0 || widthMeter < 0.0 || heightMeter < 0.0) {
            throw new OutOfCoordinateRangeException();
        }

        double width = meterToLatitude(widthMeter);
        double height = meterToLongitude(heightMeter);
        if (MIN_LATITUDE > latitude - width * 0.5
            || latitude + width * 0.5 > MAX_LATITUDE
            || MIN_LONGITUDE > longitude - height * 0.5
            || longitude + height * 0.5 > MAX_LONGITUDE
        ) {
            throw new OutOfCoordinateRangeException();
        }
    }
}
