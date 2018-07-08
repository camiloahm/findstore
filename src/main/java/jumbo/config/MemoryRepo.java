package jumbo.config;

import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jumbo.store.dto.Store;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
import java.util.Set;

/**
 * Lazy Singleton that represents an in memory data base with all the Jumbo Stores
 */
@Slf4j
@Getter
@Setter(AccessLevel.PRIVATE)
public final class MemoryRepo {

    private static MemoryRepo INSTANCE;
    private Collection<Store> stores;

    private MemoryRepo() {
    }

    public static MemoryRepo buildRepo(InputStream inputStream) {
        return INSTANCE != null ? INSTANCE : initInstance(inputStream);
    }

    private static MemoryRepo initInstance(InputStream inputStream) {
        INSTANCE = new MemoryRepo();
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Store>>() {}.getType();
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
