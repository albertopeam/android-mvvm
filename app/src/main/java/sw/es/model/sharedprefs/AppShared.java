package sw.es.model.sharedprefs;

/**
 * Created by albertopenasamor on 22/10/15.
 */
public interface AppShared {
    void put(String key, String value);
    String get(String key);
}
