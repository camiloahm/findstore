package jumbo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
class DataSourceConfiguration {

    @Bean
    MemoryRepo memoryRepo() {
        return MemoryRepo.buildRepo(getClass().getClassLoader().getResource("data/stores.json"));
    }

}
