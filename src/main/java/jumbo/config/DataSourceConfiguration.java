package jumbo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Loads any bean requires before the app starts
 */
@Component
@Configuration
@ConditionalOnProperty(name = PropertyNames.STORES_DATABASE, havingValue = "memory")
class DataSourceConfiguration {

    @Bean
    MemoryRepo memoryRepo() {
        return MemoryRepo.buildRepo(getClass().getClassLoader().getResourceAsStream("data/stores.json"));
    }

}
