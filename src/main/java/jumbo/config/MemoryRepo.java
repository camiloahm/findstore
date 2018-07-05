package jumbo.config;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import jumbo.store.dto.Store;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Collection;
import java.util.List;

@Slf4j
@Getter
public class MemoryRepo {

    private final Collection<Store> stores;

    private MemoryRepo(Collection<Store> stores) {
        this.stores = stores;
    }

    public static MemoryRepo buildRepo(URL urlLocation) {

        try {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Store>>() {
            }.getType();
            File jsonFile = new File(urlLocation.getFile());
            JsonReader reader = new JsonReader(new FileReader(jsonFile));
            return new MemoryRepo(gson.fromJson(reader, listType));
        } catch (FileNotFoundException e) {
            log.error("There was an error loading stores.json");
            throw new RuntimeException(e);
        }

    }

}
