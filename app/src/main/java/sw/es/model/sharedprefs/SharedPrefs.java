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


    private SharedPreferences.Editor getEditor() {
        return sharedPreferences.edit();
    }


    @Override
    public void put(String key, String value) {
        getEditor().putString(key, value).apply();
    }


    @Override
    public String get(String key) {
        return sharedPreferences.getString(key, "");
    }


    @Override
    public void putStrings(String key, List<String>values){
        Set<String>set = new HashSet<>(values);
        getEditor().putStringSet(key, set).apply();
    }


    @Override
    public void addString(String key, String value){
        List<String>previous = getStrings(key);
        previous.add(value);
        putStrings(key, previous);
    }



    @Override
    public List<String> getStrings(String key){
        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet(key, new HashSet<String>());

        Iterator<String> iterator = set.iterator();
        List<String>list = new ArrayList<>();
        while(iterator.hasNext()){
            list.add(iterator.next());
        }

        return list;
    }
}
