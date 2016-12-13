package Data;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by Maria on 08/12/16.
 */
public class CityPreference {
    SharedPreferences prefs;

    public CityPreference(Activity activity){
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    public String getCity() {
        return prefs.getString("city", "London");
    }

    public void setCity(String city) {
        prefs.edit().putString("city", city).commit();
    }
}
