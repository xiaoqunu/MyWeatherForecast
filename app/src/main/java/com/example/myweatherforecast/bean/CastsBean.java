package com.example.myweatherforecast.bean;

import com.google.gson.annotations.SerializedName;

public class CastsBean {
    @SerializedName("date")
    private String date;

    @SerializedName("week")
    private String week;

    @SerializedName("dayweather")
    private String dayweather;

    @SerializedName("nightweather")
    private String nightweather;

    @SerializedName("daytemp")
    private String daytemp;

    @SerializedName("nighttemp")
    private String nighttemp;

    @SerializedName("daywind")
    private String daywind;

    @SerializedName("nightwind")
    private String nightwind;

    @SerializedName("daypower")
    private String daypower;

    @SerializedName("nightpower")
    private String nightpower;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDayweather() {
        return dayweather;
    }

    public void setDayweather(String dayweather) {
        this.dayweather = dayweather;
    }

    public String getNightweather() {
        return nightweather;
    }

    public void setNightweather(String nightweather) {
        this.nightweather = nightweather;
    }

    public String getDaytemp() {
        return daytemp;
    }

    public void setDaytemp(String daytemp) {
        this.daytemp = daytemp;
    }

    public String getNighttemp() {
        return nighttemp;
    }

    public void setNighttemp(String nighttemp) {
        this.nighttemp = nighttemp;
    }

    public String getDaywind() {
        return daywind;
    }

    public void setDaywind(String daywind) {
        this.daywind = daywind;
    }

    public String getNightwind() {
        return nightwind;
    }

    public void setNightwind(String nightwind) {
        this.nightwind = nightwind;
    }

    public String getDaypower() {
        return daypower;
    }

    public void setDaypower(String daypower) {
        this.daypower = daypower;
    }

    public String getNightpower() {
        return nightpower;
    }

    public void setNightpower(String nightpower) {
        this.nightpower = nightpower;
    }

    @Override
    public String toString() {
        return "CastsBean{" +
                "date='" + date + '\'' +
                ", week='" + week + '\'' +
                ", dayweather='" + dayweather + '\'' +
                ", nightweather='" + nightweather + '\'' +
                ", daytemp='" + daytemp + '\'' +
                ", nighttemp='" + nighttemp + '\'' +
                ", daywind='" + daywind + '\'' +
                ", nightwind='" + nightwind + '\'' +
                ", daypower='" + daypower + '\'' +
                ", nightpower='" + nightpower + '\'' +
                '}';
    }
}
