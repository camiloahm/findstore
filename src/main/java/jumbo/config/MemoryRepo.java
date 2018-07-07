package jumbo.config;

import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jumbo.store.dto.Store;
import lombok.Getter;
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
 * Represents an in memory data base with all the Jumbo Stores
 */
@Slf4j
@Getter
public final class MemoryRepo {

    private final Collection<Store> stores;

    private MemoryRepo(Collection<Store> stores) {
        this.stores = stores;
    }

    public static MemoryRepo buildRepo(InputStream inputStream) {

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Store>>(){}.getType();
        Reader reader = new InputStreamReader(inputStream);
        Collection<Store> stores = gson.fromJson(reader, listType);
        return CollectionUtils.isEmpty(stores)
                ? new MemoryRepo(Collections.emptySet())
                : new MemoryRepo(stores);
    }

}
