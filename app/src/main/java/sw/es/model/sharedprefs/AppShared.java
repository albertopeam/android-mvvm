package sw.es.model.sharedprefs;

import java.util.List;

/**
 * Created by albertopenasamor on 22/10/15.
 */
public interface AppShared {
    void remove(String key);
    void put(String key, String value);
    String get(String key);

    void addStringToSet(String key, String value);
    void removeStringFromSet(String key, String value);
    void putStringsInSet(String key, List<String> strings);
    List<String> getStringsFromSet(String key);
}
