package model;

/**
 * Created by Maria on 12/12/16.
 */
public class WeatherList {
    public String getList_icon() {
        return list_icon;
    }

    public void setList_icon(String list_icon) {
        this.list_icon = list_icon;
    }

    public String getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(String min_temp) {
        this.min_temp = min_temp;
    }

    public String getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(String max_temp) {
        this.max_temp = max_temp;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getList_data() {
        return list_data;
    }

    public void setList_data(String list_data) {
        this.list_data = list_data;
    }

    public WeatherList(String list_data, String list_icon, String min_temp, String max_temp, String desc) {
        this.list_data = list_data;
        this.list_icon = list_icon;
        this.min_temp = min_temp;
        this.max_temp = max_temp;
        this.desc = desc;
    }

    public WeatherList() {
    }

    public String list_data;
    public String list_icon;
    public String min_temp;
    public String max_temp;
    public String desc;


}
