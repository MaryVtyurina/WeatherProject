package com.example.maria.weatherapp;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Data.JSONWeatherParser;
import Data.JSONWeekWeatherParser;
import Data.WeatherHttpClient;
import Data.WeekWeatherHttpClient;
import model.Weather;
import model.WeekWeather;

/**
 * Created by Maria on 08/12/16.
 */
public class SecondFragment extends Fragment {

    View myView;
    private List <TextView> days = new ArrayList<>();
    private String apikey = "415250147bf526a105828f2a4a7e6902";

    WeekWeather weekWeather = new WeekWeather();
    List<WeekWeather> weatherList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.second_layout, container, false);

            days.add((TextView) myView. findViewById(R.id.day1));
            days.add((TextView) myView. findViewById(R.id.day2));
            days.add((TextView) myView. findViewById(R.id.day3));
            days.add((TextView) myView. findViewById(R.id.day4));
            days.add((TextView) myView. findViewById(R.id.day5));
            days.add((TextView) myView. findViewById(R.id.day6));
            days.add((TextView) myView. findViewById(R.id.day7));

        renderWeatherData("Moscow,RU");

        return myView;

    }

    public void renderWeatherData(String city) {
        WeatherTask weatherTask = new WeatherTask();

        weatherTask.execute(new String[]{city +"&cnt=7" + "&APPID=" + apikey});
        System.out.println(city +"&cnt=7" + "&APPID=" + apikey);
//        weatherTask.execute(new String[]{city + "&cnt=7" + "&APPID=" + apikey});
    }

    private class WeatherTask extends AsyncTask<String, Void, List<WeekWeather>> {
        @Override
        protected List<WeekWeather> doInBackground(String... params) {
            System.out.println("1111");
            String data = ((new WeekWeatherHttpClient()).getWeatherData(params[0]));
            System.out.println(data);
            weatherList = JSONWeekWeatherParser.getWeather(data);
            Log.v("Data: ", weatherList.get(1).weekCondition.getDescription());

            return weatherList;
        }

        protected String convertDate(long unixSec) {
            Date date = new Date(unixSec * 1000L); // *1000 is to convert seconds to milliseconds
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM"); // the format of your date
            String formattedDate = sdf.format(date);
            return formattedDate;
        }

        @Override
        protected void onPostExecute(List<WeekWeather> weather) {
            super.onPostExecute(weather);

            for (int i = 0; i < 7; i++) {
                DecimalFormat decimalFormat = new DecimalFormat("#.#");
                String tempFormat = decimalFormat.format(weather.get(i).weekCondition.getMaxTemp() - 274.15);
                String tempFormat2 = decimalFormat.format(weather.get(i).weekCondition.getMinTemp() - 274.15);
//                days.get(i).setText(weather.get(i).place.getCity() + "," + weather.place.getCountry());
//                temp.setText("  " + tempFormat + "C");
                days.get(i).setText(convertDate(weather.get(i).place.getData()) + "   " + tempFormat + "C    " +
                        tempFormat2 + "C"   + weather.get(i).weekCondition.getCondition() + "("
                        + weather.get(i).weekCondition.getDescription() + ")");
            }

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.change_cityId){
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

}
