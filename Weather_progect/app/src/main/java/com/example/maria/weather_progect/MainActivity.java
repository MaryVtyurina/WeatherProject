package com.example.maria.weather_progect;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
//import android.R;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import Data.CityPreference;
import Data.JSONWeatherParser;
import Data.WeatherHttpClient;
import Util.Utils;
import model.Weather;

public class MainActivity extends AppCompatActivity {

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

    Weather weather = new Weather();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityName = (TextView) findViewById(R.id.cityText);
        iconView = (ImageView) findViewById(R.id.thumbnailIcon);
        temp = (TextView) findViewById(R.id.tempText);
        description = (TextView) findViewById(R.id.cloudText);
        humididty = (TextView) findViewById(R.id.humidText);
        pressure = (TextView) findViewById(R.id.pressureText);
        wind = (TextView) findViewById(R.id.windText);
        sunrise = (TextView) findViewById(R.id.riseText);
        sunset = (TextView) findViewById(R.id.setText);
        updated = (TextView) findViewById(R.id.updateText);

//        CityPreference cityPreference = new CityPreference(MainActivity.this);

        renderWeatherData("Moscow,RU");
    }

    public void renderWeatherData(String city) {
        WeatherTask weatherTask = new WeatherTask();
        weatherTask.execute(new String[]{city + "&APPID=" + apikey});
    }

//    private class DownloadImageAsyncTask extends AsyncTask<String, Void, Bitmap>{
//        @Override
//        protected Bitmap doInBackground(String... params) {
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Bitmap bitmap) {
//            super.onPostExecute(bitmap);
//        }
//
//    }

    private class WeatherTask extends AsyncTask<String, Void, Weather>{
        @Override
        protected Weather doInBackground(String... params) {

            String data = ((new WeatherHttpClient()).getWeatherData(params[0]));

            weather = JSONWeatherParser.getWeather(data);

            Log.v("Data: ", weather.currentCondition.getDescription());

            return weather;
        }

        protected String convertDate(long unixSec){
            Date date = new Date(unixSec*1000L); // *1000 is to convert seconds to milliseconds
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); // the format of your date
            String formattedDate = sdf.format(date);
            return formattedDate;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

//            DateFormat df = DateFormat.getTimeInstance();

//            String sunriseDate = df.format(new Date(weather.place.getSunrise()));
//            String sunsetDate = df.format(new Date(weather.place.getSunset()));
//            String updateDate = df.format(new Date(weather.place.getLastupdate()));

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

        }
    }

//    private void showInputDialog(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        builder.setTitle("Change City");
//
//        final EditText cityInput = new EditText(MainActivity.this);
//        cityInput.setInputType(InputType.TYPE_CLASS_TEXT);
//        cityInput.setHint("Portland,US");
//        builder.setView(cityInput);
//        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                CityPreference cityPreference = new CityPreference(MainActivity.this);
//                cityPreference.setCity(cityInput.getText().toString());
//
//                String newCity = cityPreference.getCity();
//
//                renderWeatherData(newCity);
//            }
//        });
//        builder.show();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.change_cityId){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
