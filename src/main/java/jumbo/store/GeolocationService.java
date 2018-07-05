package jumbo.store;

import jumbo.store.dto.Location;
import jumbo.store.dto.Store;

import java.util.Set;

public interface GeolocationService {

    Set<Store> findStoresNearbyTo(Location location);

}
