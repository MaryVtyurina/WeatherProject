package Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Util.Utils;
import model.Place;
import model.Weather;
import model.WeekPlace;
import model.WeekWeather;

/**
 * Created by Maria on 11/12/16.
 */
public class JSONWeekWeatherParser {
    public static List<WeekWeather> getWeather(String data){
        List<WeekWeather> weatherList = new ArrayList<>();
        //create JSON object from data
        try {

            JSONObject jsonObject = new JSONObject(data);

            JSONArray array = jsonObject.getJSONArray("list");




            for (int i=0; i < array.length();i++) {

                WeekWeather weekWeather = new WeekWeather();

                WeekPlace weekPlace = new WeekPlace();
                JSONObject cityObj = Utils.getObject("city", jsonObject);
                JSONObject coordObj = Utils.getObject("coord", cityObj);
                weekPlace.setLat(Utils.getFloat("lat", coordObj));
                weekPlace.setLon(Utils.getFloat("lon", coordObj));
                weekPlace.setCity(Utils.getString("name", cityObj));
                weekWeather.place = weekPlace;

                JSONObject w = array.getJSONObject(i);

                JSONArray jsonArray = w.getJSONArray("weather");
                JSONObject jsonWeather = jsonArray.getJSONObject(0);
                weekPlace.setData(Utils.getInt("dt", w));
                weekWeather.weekCondition.setWeatherId(Utils.getInt("id", jsonWeather));
                weekWeather.weekCondition.setDescription(Utils.getString("description", jsonWeather));
                weekWeather.weekCondition.setCondition(Utils.getString("main", jsonWeather));

                JSONObject tempObj = Utils.getObject("temp", w);
                weekWeather.weekCondition.setMinTemp(Utils.getFloat("min", tempObj));
                weekWeather.weekCondition.setMaxTemp(Utils.getFloat("max", tempObj));

                weatherList.add(weekWeather);
            }
            return weatherList;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
