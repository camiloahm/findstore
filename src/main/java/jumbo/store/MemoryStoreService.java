package jumbo.store;


import jumbo.config.MemoryRepo;
import jumbo.store.dto.Location;
import jumbo.store.dto.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This service contains all the operations related to Stores
 */
@Service
class MemoryStoreService implements StoreService {

    private final MemoryRepo memoryRepo;

    @Autowired
    MemoryStoreService(MemoryRepo memoryRepo) {

        this.memoryRepo = memoryRepo;
    }


    /**
     * Find All the stores
     *
     * @return Set with all the stores
     */
    @Override
    public Optional<Set<Store>> findAllStores() {
        return Optional.ofNullable(
                memoryRepo.getStores()
                        .stream()
                        .collect(Collectors.toSet()));
    }


    /**
     * Find close stores near by to a given location
     *
     * @param location
     * @param limit    Number of stores to retrieve
     * @return Set with the stores
     */
    @Override
    public Optional<Set<Store>> findStoresNearbyTo(Location location, int limit) {
        return Optional.ofNullable(
                getCloseStores(location, limit).
                        keySet().
                        stream()
                        .collect(Collectors.toSet()));

    }

    /**
     * Gets a map with close stores near by to a given location.
     * The key is the store and the value is the distance between the
     * given location and the store
     *
     * @param location
     * @param limit    Number of stores to retrieve
     * @return
     */
    private Map<Store, Double> getCloseStores(Location location, int limit) {
        return calculateStoreDistancesFromThat(location)
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(limit)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Calculates the distances between all the stores and the given location,
     * then it returns a Map with the Stores and the distances from that store
     * to the given location
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
