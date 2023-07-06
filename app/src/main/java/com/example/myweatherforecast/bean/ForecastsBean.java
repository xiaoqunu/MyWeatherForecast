package com.example.myweatherforecast.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastsBean {
    @SerializedName("adcode")
    private String adcode;

    @SerializedName("city")
    private String city;

    @SerializedName("reporttime")
    private String reporttime;

    @SerializedName("casts")
    private List<CastsBean> casts;

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

    public List<CastsBean> getCasts() {
        return casts;
    }

    public void setCasts(List<CastsBean> casts) {
        this.casts = casts;
    }

    @Override
    public String toString() {
        return "ForecastsBean{" +
                "adcode='" + adcode + '\'' +
                ", city='" + city + '\'' +
                ", reporttime='" + reporttime + '\'' +
                ", casts=" + casts +
                '}';
    }
}
