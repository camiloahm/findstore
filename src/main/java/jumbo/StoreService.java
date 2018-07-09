package jumbo;

import jumbo.dto.Location;
import jumbo.dto.Store;

import java.util.Optional;
import java.util.Set;

/**
 * This service can find Jumbo Stores Near by to some given location
 */
public interface StoreService {

    Optional<Set<Store>> findStoresNearbyTo(Location location, int limit);

    Optional<Set<Store>> findAllStores();

}
