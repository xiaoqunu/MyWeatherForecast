package com.example.myweatherforecast.bean;

import com.google.gson.annotations.SerializedName;

public class LivesBean {

    @SerializedName("adcode")
    private String adcode;

    @SerializedName("city")
    private String city;

    @SerializedName("reporttime")
    private String reporttime;

    @SerializedName("weather")
    private String weather;

    @SerializedName("temperature")
    private String temperature;

    @SerializedName("winddirection")
    private String winddirection;

    @SerializedName("windpower")
    private String windpower;

    @SerializedName("humidity")
    private String humidity;

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getReporttime() {
        return reporttime;
    }

    public void setReporttime(String reporttime) {
        this.reporttime = reporttime;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWinddirection() {
        return winddirection;
    }

    public void setWinddirection(String winddirection) {
        this.winddirection = winddirection;
    }

    public String getWindpower() {
        return windpower;
    }

    public void setWindpower(String windpower) {
        this.windpower = windpower;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "LivesBean{" +
                "adcode='" + adcode + '\'' +
                ", city='" + city + '\'' +
                ", reporttime='" + reporttime + '\'' +
                ", weather='" + weather + '\'' +
                ", temperature='" + temperature + '\'' +
                ", winddirection='" + winddirection + '\'' +
                ", windpower='" + windpower + '\'' +
                ", humidity='" + humidity + '\'' +
                '}';
    }
}
