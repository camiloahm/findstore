package jumbo;

import jumbo.config.MemoryRepo;
import jumbo.dto.Location;
import jumbo.dto.Store;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MemoryStoreServiceTest {

    private MemoryRepo memoryRepo;
    private StoreService storeService;

    @Before
    public void before() {
        memoryRepo = MemoryRepo.buildRepo(getClass().getClassLoader().getResourceAsStream("data/storestest.json"));
        storeService = new MemoryStoreService(memoryRepo);
    }

    @Test
    public void testFindStoresNearbyToWithNullLocation() {
        Optional<Set<Store>> response = storeService.findStoresNearbyTo(null, 0);
        assertThat(response.get()).isEmpty();
    }

    @Test
    public void testFindStoresNearbyWithCorrectLocationReturnsLocations() {
        Location loc1 = Location.builder()
                .latitude(74.640832)
                .longitude(40.366633)
                .build();

        Optional<Set<Store>> response = storeService.findStoresNearbyTo(loc1, 2);
        assertThat(response.get()).isNotEmpty().hasSize(2);
    }

    @Test
    public void testFindStoresNearbyWithCorrectLocationReturnsCorrectLocation() {
        Location loc1 = Location.builder()
                .latitude(74.640832)
                .longitude(40.366633)
                .build();

        Store store1 = Store.builder()
                .city("Aalten")
                .postalCode("7122 WJ")
                .street("Admiraal de Ruyterstraat")
                .street2("10")
                .street3("")
                .addressName("Jumbo Aalten Leussink")
                .uuid("0XcKYx4XNRQAAAFI3LgYwKxK")
                .longitude(6.576066)
                .latitude(51.923993)
                .complexNumber("30519")
                .showWarningMessage(true)
                .todayOpen("08:00")
                .todayClose("21:00")
                .locationType("SupermarktPuP")
                .collectionPoint(true)
                .sapStoreID("6487")
                .build();

        Store store2 = Store.builder()
                .city("Aalsmeer")
                .postalCode("1431 HN")
                .street("Ophelialaan")
                .street2("124")
                .street3("")
                .addressName("Jumbo Aalsmeer Ophelialaan")
                .uuid("gssKYx4XJwoAAAFbn.BMqPTb")
                .longitude(4.762433)
                .latitude(52.264417)
                .complexNumber("33010")
                .showWarningMessage(true)
                .todayOpen("08:00")
                .locationType("SupermarktPuP")
                .collectionPoint(true)
                .sapStoreID("3178")
                .todayClose("22:00")
                .build();


        Optional<Set<Store>> response = storeService.findStoresNearbyTo(loc1, 2);
        assertThat(response.get()).contains(store1, store2);
        assertThat(response.get()).hasSize(2);
    }


    @Test
    public void testFindAllStores() {
        Optional<Set<Store>> response = storeService.findAllStores();
        assertThat(response.get()).isNotEmpty().hasSize(5);
    }

    @Test
    public void testFindNearByLimitResultWithZeroLimit() {

        MemoryRepo memoryRepoMock = mock(MemoryRepo.class);
        StoreService storeService = new MemoryStoreService(memoryRepoMock);


        Store store1 = Store.builder()
                .city("Aalten")
                .postalCode("7122 WJ")
                .street("Admiraal de Ruyterstraat")
                .street2("10")
                .street3("")
                .addressName("Jumbo Aalten Leussink")
                .uuid("0XcKYx4XNRQAAAFI3LgYwKxK")
                .longitude(6.576066)
                .latitude(51.923993)
                .complexNumber("30519")
                .showWarningMessage(true)
                .todayOpen("08:00")
                .todayClose("21:00")
                .locationType("SupermarktPuP")
                .collectionPoint(true)
                .sapStoreID("6487")
                .build();

        Set<Store> result = new HashSet<>();
        result.add(store1);

        when(memoryRepoMock.getStores()).thenReturn(result);

        Location loc1 = Location.builder()
                .latitude(74.640832)
                .longitude(40.366633)
                .build();

        Optional<Set<Store>> response = storeService.findStoresNearbyTo(loc1, 0);

        assertThat(response.get()).isEmpty();

    }


    @Test
    public void testFindNearByLimitResultWithOneLimit() {

        MemoryRepo memoryRepoMock = mock(MemoryRepo.class);
        StoreService storeService = new MemoryStoreService(memoryRepoMock);


        Store store1 = Store.builder()
                .city("Aalten")
                .postalCode("7122 WJ")
                .street("Admiraal de Ruyterstraat")
                .street2("10")
                .street3("")
                .addressName("Jumbo Aalten Leussink")
                .uuid("0XcKYx4XNRQAAAFI3LgYwKxK")
                .longitude(6.576066)
                .latitude(51.923993)
                .complexNumber("30519")
                .showWarningMessage(true)
                .todayOpen("08:00")
                .todayClose("21:00")
                .locationType("SupermarktPuP")
                .collectionPoint(true)
                .sapStoreID("6487")
                .build();

        Set<Store> result = new HashSet<>();
        result.add(store1);

        when(memoryRepoMock.getStores()).thenReturn(result);

        Location loc1 = Location.builder()
                .latitude(74.640832)
                .longitude(40.366633)
                .build();

        Optional<Set<Store>> response = storeService.findStoresNearbyTo(loc1, 1);

        assertThat(response.get()).hasSize(1);

    }
}
