package jumbo.store.config;

import jumbo.config.MemoryRepo;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class MemoryRepoTest {

    @Test
    public void testBuildMemoryRepo() {
        MemoryRepo memoryRepo = MemoryRepo.buildRepo(getClass().getClassLoader().getResourceAsStream("data/storestest.json"));
        assertThat(memoryRepo.getStores()).isNotEmpty();
    }
}
