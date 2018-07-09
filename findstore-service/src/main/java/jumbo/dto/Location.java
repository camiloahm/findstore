package jumbo.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Represents a store location
 */
@Getter
@Builder
@EqualsAndHashCode
public class Location {

    private double longitude;
    private double latitude;

    /**
     * return distance between this location and that location
     * measured in statute miles
     *
     * @param that
     * @return distance from this location to that
     */
    public Double distanceTo(Location that) {

        if (that == null) {
            return 0D;
        }

        final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;
        final double lat1 = Math.toRadians(this.latitude);
        final double lon1 = Math.toRadians(this.longitude);
        final double lat2 = Math.toRadians(that.latitude);
        final double lon2 = Math.toRadians(that.longitude);

        // great circle distance in radians, using law of cosines formula
        double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

        // each degree on a great circle of Earth is 60 nautical miles
        double nauticalMiles = 60 * Math.toDegrees(angle);
        double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
        return statuteMiles;
    }

}