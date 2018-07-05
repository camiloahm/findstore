package jumbo.store;


import jumbo.config.MemoryRepo;
import jumbo.store.dto.Location;
import jumbo.store.dto.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This service can find Jumbo Stores Near by to some given location, using an in Memory
 * Database created from a JSON file; a better approach would be to store that data inside
 * a NoSQL Database like Elasticsearch or Mongo which have capabilities to aggregate
 * geolocations
 */
@Service
class MemoryGeolocationService implements GeolocationService {

    private final MemoryRepo memoryRepo;

    @Autowired
    MemoryGeolocationService(MemoryRepo memoryRepo) {

        this.memoryRepo = memoryRepo;
    }

    /**
     * Find 5 close stores near by to a given location
     *
     * @param location
     * @return Set with 5 stores
     */
    @Override
    public Set<Store> findStoresNearbyTo(Location location) {
        return getCloseStores(location).
                keySet().
                stream()
                .collect(Collectors.toSet());

    }

    /**
     * Gets a map with 5 close stores near by to a given location.
     * The key is the store and the value is the distance between the
     * given location and the store
     *
     * @param location
     * @return
     */
    private Map<Store, Double> getCloseStores(Location location) {
        return calculateStoreDistancesFromThat(location)
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Calculates the distances between all the stores and the given location, then it returns a Map with the Store and the
     * distance from that store to the given location
     *
     * @param location
     * @return Map with the Stores and the distances from each store to the given location
     */
    private Map<Store, Double> calculateStoreDistancesFromThat(Location location) {
        return memoryRepo.getStores()
                .stream()
                .collect(Collectors.toMap(store -> store, store -> store.getLocation().distanceTo(location)));
    }

}
