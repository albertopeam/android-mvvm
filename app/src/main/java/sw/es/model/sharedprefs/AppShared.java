package sw.es.model.sharedprefs;

import java.util.List;

/**
 * Created by albertopenasamor on 22/10/15.
 */
public interface AppShared {
    void put(String key, String value);
    String get(String key);
    void putStrings(String key, List<String> strings);
    List<String> getStrings(String key);
}
