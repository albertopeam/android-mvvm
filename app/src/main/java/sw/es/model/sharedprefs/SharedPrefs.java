package sw.es.model.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

/**
 * Created by albertopenasamor on 22/10/15.
 */
public class SharedPrefs implements AppShared{


    private static final String SHARED_PREFERENCES = "shared_preferences";
    private SharedPreferences sharedPreferences;


    @Inject
    public SharedPrefs(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }


    @Override
    public void remove(String key) {
        getEditor().remove(key).apply();
    }

    @Override
    public void put(String key, String value) {
        getEditor().putString(key, value).apply();
    }


    @Override
    public String get(String key) {
        return getShared().getString(key, "");
    }


    @Override
    public void putStringsInSet(String key, List<String> values){
        Set<String>set = new HashSet<>(values);
        getEditor().putStringSet(key, set).apply();
    }


    @Override
    public void addStringToSet(String key, String value){
        List<String>previous = getStringsFromSet(key);
        previous.add(value);
        putStringsInSet(key, previous);
    }

    @Override
    public void removeStringFromSet(String key, String value) {
        HashSet<String> set = getSet(key);

        set.remove(value);
        List<String>list = convertSetToList(set);
        putStringsInSet(key, list);
    }


    @Override
    public List<String> getStringsFromSet(String key){
        HashSet<String> set = getSet(key);
        return convertSetToList(set);
    }


    private HashSet<String> getSet(String key){
        HashSet<String> set = (HashSet<String>) getShared().getStringSet(key, new HashSet<String>());
        return set;
    }


    private List<String>convertSetToList(HashSet<String> set){
        Iterator<String> iterator = set.iterator();
        List<String>list = new ArrayList<>();
        while(iterator.hasNext()){
            list.add(iterator.next());
        }
        return list;
    }


    private SharedPreferences.Editor getEditor() {
        return sharedPreferences.edit();
    }


    private SharedPreferences getShared() {
        return sharedPreferences;
    }


}
