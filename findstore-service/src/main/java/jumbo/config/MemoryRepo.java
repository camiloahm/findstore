package jumbo.config;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jumbo.dto.Store;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Lazy Singleton that represents an in memory data base with all the Jumbo Stores
 */
@Slf4j
@Getter
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode
public class MemoryRepo {

    private static MemoryRepo INSTANCE;
    private Collection<Store> stores;

    private MemoryRepo() {
    }

    public static MemoryRepo buildRepo(InputStream inputStream) {
        return INSTANCE == null ? initInstance(inputStream) : INSTANCE;
    }

    private static MemoryRepo initInstance(InputStream inputStream) {
        INSTANCE = new MemoryRepo();
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Store>>() {
        }.getType();
        Reader reader = new InputStreamReader(inputStream);
        Collection<Store> stores = gson.fromJson(reader, listType);

        if (CollectionUtils.isEmpty(stores)) {
            INSTANCE.setStores(Collections.emptySet());
        } else {
            INSTANCE.setStores(stores);
        }

        return INSTANCE;
    }

}
