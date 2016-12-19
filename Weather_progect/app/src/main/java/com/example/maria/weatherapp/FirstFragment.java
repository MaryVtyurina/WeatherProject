package com.example.maria.weatherapp;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Data.CityActive;
import Data.CityPreference;
import Data.JSONWeatherParser;
import Data.WeatherHttpClient;
import model.Weather;

/**
 * Created by Maria on 08/12/16.
 */
public class FirstFragment extends Fragment {

    private TextView cityName;
    private TextView temp;
    private ImageView iconView;
    private TextView description;
    private TextView humididty;
    private TextView pressure;
    private TextView wind;
    private TextView sunrise;
    private TextView sunset;
    private TextView updated;
    private String apikey = "415250147bf526a105828f2a4a7e6902";

    int[] icons = {R.drawable.icon_01d,
            R.drawable.icon_02d,
            R.drawable.icon_03d,
            R.drawable.icon_04d,
            R.drawable.icon_09d,
            R.drawable.icon_10d,
            R.drawable.icon_11d,
            R.drawable.icon_13d,
            R.drawable.icon_50d};
    int[] iconss = {R.drawable.icon_01n,
            R.drawable.icon_02n,
            R.drawable.icon_03n,
            R.drawable.icon_04n,
            R.drawable.icon_09n,
            R.drawable.icon_10n,
            R.drawable.icon_11n,
            R.drawable.icon_13n};

    Weather weather = new Weather();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.first_layout, container, false);

        iconView = (ImageView) myView.findViewById(R.id.thumbnailIcon);
        cityName = (TextView) myView. findViewById(R.id.cityText);
        temp = (TextView) myView.findViewById(R.id.tempText);
        description = (TextView) myView.findViewById(R.id.cloudText);
        humididty = (TextView)myView. findViewById(R.id.humidText);
        pressure = (TextView)myView. findViewById(R.id.pressureText);
        wind = (TextView) myView.findViewById(R.id.windText);
        sunrise = (TextView)myView. findViewById(R.id.riseText);
        sunset = (TextView) myView.findViewById(R.id.setText);
        updated = (TextView) myView.findViewById(R.id.updateText);

//        List<CityActive> cityActiveList = CityActive.getAllWeather();
//        String cityin = cityActiveList.get(0).getName();
//        Log.e("TAG", "cityinput" + cityActiveList.get(0).getName());
//        renderWeatherData(cityin);

        CityPreference cityPreference = new CityPreference(this.getActivity());
        renderWeatherData(cityPreference.getCity());

//        Bundle bundle = this.getArguments();
//        if (bundle != null) {
//            String i = bundle.getString("city");
//            System.out.println(i);
//            renderWeatherData(i);
//        }
//        else {
//
//            String city = "Moscow";
//
//            renderWeatherData(city);
//
//        }
        return myView;
    }

    public void renderWeatherData(String city) {
        WeatherTask weatherTask = new WeatherTask();


        weatherTask.execute(new String[]{city + "&APPID=" + apikey});
    }

    private class WeatherTask extends AsyncTask<String, Void, Weather> {
        @Override
        protected Weather doInBackground(String... params) {

            String data = ((new WeatherHttpClient()).getWeatherData(params[0]));

            weather = JSONWeatherParser.getWeather(data);

            Log.v("Data: ", weather.currentCondition.getDescription());

            return weather;
        }

        protected String convertDate(long unixSec) {
            Date date = new Date(unixSec * 1000L); // *1000 is to convert seconds to milliseconds
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); // the format of your date
            String formattedDate = sdf.format(date);
            return formattedDate;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);


            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            String tempFormat = decimalFormat.format(weather.currentCondition.getTemperature() - 274.15);

            cityName.setText(weather.place.getCity() + "," + weather.place.getCountry());
            temp.setText("  " + tempFormat + "C");
            humididty.setText("Humidity: " + weather.currentCondition.getHumidity() + "%");
            pressure.setText("Pressure: " + weather.currentCondition.getPressure() + " hPa");
            wind.setText("Wind: " + weather.wind.getSpeed() + " mps");
            sunrise.setText("Sunrise: " + convertDate(weather.place.getSunrise()));
            sunset.setText("Sunset: " + convertDate(weather.place.getSunset()));
            updated.setText("Last Updated: " + convertDate(weather.place.getLastupdate()));
            description.setText("Condition: " + weather.currentCondition.getCondition() + "("
                    + weather.currentCondition.getDescription() + ")");

            System.out.println(weather.currentCondition.getIcon());
            switch (weather.currentCondition.getIcon()) {
                case "01d":
                    iconView.setImageResource(icons[0]);
                    break;
                case "02d":
                    iconView.setImageResource(icons[1]);
                    break;
                case "03d":
                    iconView.setImageResource(icons[2]);
                    break;
                case "04d":
                    iconView.setImageResource(icons[3]);
                    break;
                case "09d":
                    iconView.setImageResource(icons[4]);
                    break;
                case "10d":
                    iconView.setImageResource(icons[5]);
                    break;
                case "11d":
                    iconView.setImageResource(icons[6]);
                    break;
                case "13d":
                    iconView.setImageResource(icons[7]);
                    break;
                case "50d":
                    iconView.setImageResource(icons[8]);
                    break;
                case "01n":
                    iconView.setImageResource(iconss[0]);
                    break;
                case "02n":
                    iconView.setImageResource(iconss[1]);
                    break;
                case "03n":
                    iconView.setImageResource(iconss[2]);
                    break;
                case "04n":
                    iconView.setImageResource(iconss[3]);
                    break;
                case "09n":
                    iconView.setImageResource(iconss[4]);
                    break;
                case "10n":
                    iconView.setImageResource(iconss[5]);
                    break;
                case "11n":
                    iconView.setImageResource(iconss[6]);
                    break;
                case "13n":
                    iconView.setImageResource(iconss[7]);
                    break;
                case "50n":
                    iconView.setImageResource(icons[8]);
                    break;
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings){
//            showInputDialog();
        }

        return super.onOptionsItemSelected(item);
    }
}
