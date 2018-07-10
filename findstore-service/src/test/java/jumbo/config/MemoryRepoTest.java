package jumbo.config;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class MemoryRepoTest {

    @Test
    public void testBuildMemoryRepo() {
        MemoryRepo memoryRepo = MemoryRepo.buildRepo(getClass().getClassLoader().getResourceAsStream("data/storestest.json"));
        assertThat(memoryRepo.getStores()).isNotEmpty();
    }

    @Test
    public void testSingletonInitialization() {
        MemoryRepo memoryRepo1 = MemoryRepo.buildRepo(getClass().getClassLoader().getResourceAsStream("data/storestest.json"));
        MemoryRepo memoryRepo2 = MemoryRepo.buildRepo(null);

        assertThat(memoryRepo1).isEqualTo(memoryRepo2);
    }
}
