package model;

/**
 * Created by Maria on 11/12/16.
 */
public class WeekTemperature {
    private float minTemp;
    private float maxTemp;
    private String getIcon;

    public String getGetIcon() {
        return getIcon;
    }

    public void setGetIcon(String getIcon) {
        this.getIcon = getIcon;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(float maxTemp) {
        this.maxTemp = maxTemp;
    }
}
