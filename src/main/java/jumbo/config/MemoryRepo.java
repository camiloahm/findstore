package jumbo.config;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jumbo.store.dto.Store;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

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
        Type listType = new TypeToken<List<Store>>() {
        }.getType();
        Reader reader = new InputStreamReader(inputStream);
        return new MemoryRepo(gson.fromJson(reader, listType));
    }

}
