package jumbo;


import jumbo.dto.Location;
import jumbo.dto.Store;
import org.junit.Test;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class StoreControllerTest {

    @Test
    public void testFindCloseStoreWithInvalidLongitude() {

        StoreService storeService = mock(StoreService.class);
        StoreController storeController = new StoreController(storeService);

        assertThatThrownBy(() -> {
            storeController.findCloseStores(null, 50D, 2);
        }).hasMessage("Longitude can't be null");

    }


    @Test()
    public void testFindCloseStoreWithInvalidLatitude() {

        StoreService storeService = mock(StoreService.class);
        StoreController storeController = new StoreController(storeService);

        assertThatThrownBy(() -> {
            storeController.findCloseStores(50D, null, 2);
        }).hasMessage("Latitude can't be null");
    }


    @Test()
    public void testFindCloseStoreWithInvalidLimit() {

        StoreService storeService = mock(StoreService.class);
        StoreController storeController = new StoreController(storeService);

        assertThatThrownBy(() -> {
            storeController.findCloseStores(50D, 5D, null);
        }).hasMessage("Limit can't be null or negative");
    }


    @Test()
    public void testFindCloseStoreWithNegativeLimit() {

        StoreService storeService = mock(StoreService.class);
        StoreController storeController = new StoreController(storeService);

        assertThatThrownBy(() -> {
            storeController.findCloseStores(50D, 5D, -3);
        }).hasMessage("Limit can't be null or negative");
    }

    @Test()
    public void testFindOneStoreWithEmptyLimit() {

        StoreService storeService = mock(StoreService.class);
        StoreController storeController = new StoreController(storeService);

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

        Set<Store> stores = new HashSet<>();
        stores.add(store1);

        when(storeService.findStoresNearbyTo(loc1, 2)).thenReturn(java.util.Optional.ofNullable(stores));
        assertThat(storeController.findCloseStores(50.0, 5.0, 0)).isEmpty();

    }

    @Test()
    public void testFindOneStoreWithLimit1() {

        StoreService storeService = mock(StoreService.class);
        StoreController storeController = new StoreController(storeService);

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

        Set<Store> stores = new HashSet<>();
        stores.add(store1);

        when(storeService.findStoresNearbyTo(loc1, 2)).thenReturn(java.util.Optional.ofNullable(stores));
        assertThat(storeController.findCloseStores(loc1.getLongitude(), loc1.getLatitude(), 2).size()).isEqualTo(1);

    }

    @Test
    public void testFindStoresWithEmptyRepo() {
        StoreService storeService = mock(StoreService.class);
        StoreController storeController = new StoreController(storeService);

        when(storeService.findAllStores()).thenReturn(Optional.empty());
        assertThat(storeController.findStores()).isEmpty();
    }

    @Test
    public void testFindStoresWithLoadedRepo() {
        StoreService storeService = mock(StoreService.class);
        StoreController storeController = new StoreController(storeService);

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

        Set<Store> stores = new HashSet<>();
        stores.add(store1);

        when(storeService.findAllStores()).thenReturn(Optional.of(stores));
        assertThat(storeController.findStores()).isNotEmpty().hasSize(1);
    }


}
