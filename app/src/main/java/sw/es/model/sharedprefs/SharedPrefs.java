package sw.es.model.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by albertopenasamor on 22/10/15.
 */
public class SharedPrefs implements AppShared{

    private static final String SHARED_PREFERENCES = "shared_preferences";
    private SharedPreferences sharedPreferences;

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
}
