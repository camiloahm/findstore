package jumbo;

import com.google.common.collect.ImmutableSet;
import jumbo.dto.Location;
import jumbo.dto.Store;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static com.google.common.base.Preconditions.checkArgument;

@RestController
@RequestMapping("/stores")
@Slf4j
class StoreController {


    private final StoreService storeService;

    @Autowired
    StoreController(StoreService storeService) {

        this.storeService = storeService;
    }

    @GetMapping(value = "/findCloseStores", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Store> findCloseStores(@RequestParam("longitude") Double longitude,
                                             @RequestParam("latitude") Double latitude,
                                             @RequestParam(value = "limit", required = false, defaultValue = "5") Integer limit) {

        log.info("Request: findCloseStores with params longitude {} latitude {}", longitude, latitude);

        checkArgument(longitude != null, "Longitude can't be null");
        checkArgument(latitude != null, "Latitude can't be null");
        checkArgument(limit != null && limit >= 0, "Limit can't be null or negative");

        return storeService.
                findStoresNearbyTo(Location
                                .builder()
                                .latitude(latitude)
                                .longitude(longitude)
                                .build()
                        , limit)
                .orElse(ImmutableSet.of());
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Store> findStores() {

        log.info("Request: findStores");

        return storeService
                .findAllStores()
                .orElse(ImmutableSet.of());
    }

}
