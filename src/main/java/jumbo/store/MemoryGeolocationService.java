package jumbo.store;


import jumbo.config.MemoryRepo;
import jumbo.store.dto.Location;
import jumbo.store.dto.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
class MemoryGeolocationService implements GeolocationService {

    private final MemoryRepo memoryRepo;

    @Autowired
    MemoryGeolocationService(MemoryRepo memoryRepo) {

        this.memoryRepo = memoryRepo;
    }

    @Override
    public Set<Store> findStoresNearbyTo(Location location) {
        return getCloseStores(location).
                keySet().
                stream()
                .collect(Collectors.toSet());

    }

    private Map<Store, Double> getCloseStores(Location location) {
        return calculateStoreDistancesFromThat(location)
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Map<Store, Double> calculateStoreDistancesFromThat(Location location) {
        return memoryRepo.getStores()
                .stream()
                .collect(Collectors.toMap(store -> store, store -> store.getLocation().distanceTo(location)));
    }

}
