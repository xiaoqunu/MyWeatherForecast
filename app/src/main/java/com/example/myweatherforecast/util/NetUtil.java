package com.example.myweatherforecast.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetUtil {

    public static final String URL_WEATHER_WITH_FUTURE = "https://restapi.amap.com/v3/weather/weatherInfo?key=bcd8710035ca7835a08dcab6264fc2f0";

    public static String doGet(String urlstr) {
        String result = "";
        HttpURLConnection connection = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        //连接网络
        try {
            URL url1 = new URL(urlstr);
            connection = (HttpURLConnection) url1.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);

            //从连接中读取数据(二进制)
            InputStream inputStream = connection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            //二进制流送入缓冲区
            bufferedReader = new BufferedReader(inputStreamReader);
            //从缓冲区按行读取字符串
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            result = stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }

            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }

    /**
     * 获取全部天气数据
     */
    public static  String getWeatherOfAllCity(String city) {
        //拼接出获取天气数据的URL
        //例子:https://restapi.amap.com/v3/weather/weatherInfo?key=bcd8710035ca7835a08dcab6264fc2f0&city=110101&extensions=all&output=JSON
        String weatherUrl = URL_WEATHER_WITH_FUTURE + "&city=" + city + "&extensions=all&output=JSON";
        Log.d("fan", "----weatherUrl----" + weatherUrl);
        String weatherResult = doGet(weatherUrl);
        Log.d("fan", "----weatherResult----" + weatherResult);
        return weatherResult;
    }

    /**
     * 获取实时天气数据
     */
    public static  String getWeatherOfBaseCity(String city) {
        //拼接出获取天气数据的URL
        //例子:https://restapi.amap.com/v3/weather/weatherInfo?key=bcd8710035ca7835a08dcab6264fc2f0&city=110101&extensions=base&output=JSON
        String weatherUrl = URL_WEATHER_WITH_FUTURE + "&city=" + city + "&extensions=base&output=JSON";
        Log.d("fan", "----weatherUrl----" + weatherUrl);
        String weatherResult = doGet(weatherUrl);
        Log.d("fan", "----weatherResult----" + weatherResult);
        return weatherResult;
    }

}
