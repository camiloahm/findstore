package jumbo.store;

import jumbo.store.dto.Location;
import jumbo.store.dto.Store;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.Collection;

@RestController
@RequestMapping("/stores")
@Slf4j
class StoreController {


    private final GeolocationService geolocationService;

    @Autowired
    StoreController(GeolocationService geolocationService) {

        this.geolocationService = geolocationService;
    }

    @RequestMapping("/findCloseStores")
    public Collection<Store> findCloseStores(@QueryParam("longitude") double longitude,
                                             @QueryParam("latitude") double latitude) {

        log.info("Request: findCloseStores with params longitude {} latitude {}", longitude, latitude);

        return geolocationService.findStoresNearbyTo(Location
                .builder()
                .latitude(latitude)
                .longitude(longitude)
                .build());
    }
}
