package jumbo.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LocationTest {

    @Test
    public void testGetDistanceTo() {

        Location loc1 = Location.builder()
                .latitude(74.640832)
                .longitude(40.366633)
                .build();
        Location loc2 = Location.builder()
                .latitude(76.488707)
                .longitude(42.443087)
                .build();
        double distance = loc1.distanceTo(loc2);

        assertThat(distance).isEqualTo(132.48130168871572);
    }


}
