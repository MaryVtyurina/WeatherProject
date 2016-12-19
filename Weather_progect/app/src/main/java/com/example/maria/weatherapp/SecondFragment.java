package com.example.maria.weatherapp;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Data.CityActive;
import Data.CityPreference;
import Data.JSONWeekWeatherParser;
import Data.WeekWeatherHttpClient;
import model.RecyclerAdapter;
import model.WeatherList;
import model.WeekWeather;

/**
 * Created by Maria on 08/12/16.
 */
public class SecondFragment extends Fragment {

    View myView;
    private String apikey = "415250147bf526a105828f2a4a7e6902";

    WeekWeather weekWeather = new WeekWeather();
    List<WeekWeather> weatherList = new ArrayList<>();
    List<WeatherList> wlist = new ArrayList<>();


    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.second_layout, container, false);
        recyclerView = (RecyclerView)myView.findViewById(R.id.rcView);

        LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);

        CityPreference cityPreference = new CityPreference(this.getActivity());
        renderWeatherData(cityPreference.getCity());

        return myView;

    }

    public List<WeatherList> getData(List<WeekWeather> weather){
        for (int i = 0; i < 7; i++) {
            WeatherList l = new WeatherList();
            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            String tempFormat = decimalFormat.format(weather.get(i).weekCondition.getMaxTemp() - 274.15);
            String tempFormat2 = decimalFormat.format(weather.get(i).weekCondition.getMinTemp() - 274.15);

            l.list_icon = weather.get(i).weekTemperature.getGetIcon();
            l.list_data = convertDate(weather.get(i).place.getData());
            l.desc = weather.get(i).weekCondition.getDescription();
            l.max_temp = tempFormat + "C";
            l.min_temp = tempFormat2 + "C";
            wlist.add(l);

        }
        return wlist;

    }

    protected String convertDate(long unixSec) {
        Date date = new Date(unixSec * 1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM"); // the format of your date
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    public void renderWeatherData(String city) {
        WeatherTask weatherTask = new WeatherTask();

        weatherTask.execute(new String[]{city +"&cnt=7" + "&APPID=" + apikey});
    }

    private class WeatherTask extends AsyncTask<String, Void, List<WeekWeather>> {
        @Override
        protected List<WeekWeather> doInBackground(String... params) {

            String data = ((new WeekWeatherHttpClient()).getWeatherData(params[0]));
            weatherList = JSONWeekWeatherParser.getWeather(data);

            return weatherList;
        }


        @Override
        protected void onPostExecute(List<WeekWeather> weather) {
            super.onPostExecute(weather);

            for (int i = 0; i < 7; i++) {
                WeatherList l = new WeatherList();
                DecimalFormat decimalFormat = new DecimalFormat("#.#");
                String tempFormat = decimalFormat.format(weather.get(i).weekCondition.getMaxTemp() - 274.15);
                String tempFormat2 = decimalFormat.format(weather.get(i).weekCondition.getMinTemp() - 274.15);
                l.list_icon = weather.get(i).weekCondition.getIconId();
                l.list_data = convertDate(weather.get(i).place.getData());
                l.desc = weather.get(i).weekCondition.getDescription();
                l.max_temp = tempFormat + "C";
                l.min_temp = tempFormat2 + "C";
                wlist.add(l);

            }

            RecyclerAdapter adapter = new RecyclerAdapter(getActivity(), wlist);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

}
