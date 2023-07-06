package com.example.myweatherforecast.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherBean {
    @SerializedName("forecasts")
    private List<ForecastsBean> forecasts;
    private List<LivesBean> lives;

    public List<ForecastsBean> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<ForecastsBean> forecasts) {
        this.forecasts = forecasts;
    }

    public List<LivesBean> getLives() {
        return lives;
    }

    public void setLives(List<LivesBean> lives) {
        this.lives = lives;
    }

    @Override
    public String toString() {
        return "WeatherBean{" +
                "forecasts=" + forecasts +
                ", lives=" + lives +
                '}';
    }
}
