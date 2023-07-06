package com.example.myweatherforecast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myweatherforecast.adapter.FutureWeatherAdapter;
import com.example.myweatherforecast.bean.CastsBean;
import com.example.myweatherforecast.bean.ForecastsBean;
import com.example.myweatherforecast.bean.LivesBean;
import com.example.myweatherforecast.bean.WeatherBean;
import com.example.myweatherforecast.util.NetUtil;
import com.google.gson.Gson;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private AppCompatSpinner mSpinner;
    private ArrayAdapter<String> mSpAdapter;
    private String[] mCities;
    //声明各种控件
    private TextView tvWeather, tvTem, tvTemLowHigh, tvWin, tvAir;
    private ImageView ivWeather;
    private RecyclerView rlvFutureWeather;
    private FutureWeatherAdapter mWeatherAdapter;

    private Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            WeatherBean weatherBean = new WeatherBean();
            WeatherBean weatherBean1 = new WeatherBean();
            WeatherBean weatherBean2 = new WeatherBean();
            if (msg.what == 0) {
                String weather = (String) msg.obj;
                Log.d("fan", "---主线程收到了天气数据---" + weather);

                String[] weather_array = weather.split("#");
                String weather1 = weather_array[0];
                String weather2 = weather_array[1];
                Gson gson = new Gson();
                weatherBean1 = gson.fromJson(weather1, WeatherBean.class);
                weatherBean2 = gson.fromJson(weather2, WeatherBean.class);
                weatherBean.setForecasts(weatherBean1.getForecasts());
                weatherBean.setLives(weatherBean2.getLives());
                Log.d("fan", "---解析后的天气数据---" + weatherBean.toString());

                updateUiOfWeather(weatherBean);
                Toast.makeText(MainActivity.this, "刷新成功！", Toast.LENGTH_SHORT).show();
            }
        }
    };

    /**
     * 刷新界面并展示天气数据
     */
    private void updateUiOfWeather(WeatherBean weatherBean) {
        if (weatherBean == null) {
            return;
        }
        //获取全部天气中的第一天
        List<ForecastsBean> forecastsBeans = weatherBean.getForecasts();
        ForecastsBean forecastsBean = forecastsBeans.get(0);
        List<CastsBean> castsBeans = forecastsBean.getCasts();
        CastsBean todayAllWeather = castsBeans.get(0);
        //获取实时天气
        List<LivesBean> livesBeans = weatherBean.getLives();
        LivesBean todayBaseWeather = livesBeans.get(0);

        if (todayAllWeather == null || todayBaseWeather == null) {
            return;
        }
        //获取气温！
        tvTem.setText(todayBaseWeather.getTemperature() + "°C");
        //获取天气和日期！
        if(todayAllWeather.getDayweather().equals(todayAllWeather.getNightweather())) {
            tvWeather.setText(todayBaseWeather.getWeather() + "(" + todayAllWeather.getDate() + ToChineseWeek(todayAllWeather.getWeek()) + ")");
        }else {
            tvWeather.setText(todayAllWeather.getDayweather() + "转" + todayAllWeather.getNightweather() + "(" + todayAllWeather.getDate() + ToChineseWeek(todayAllWeather.getWeek()) + ")");
        }
        //获取温差！
        tvTemLowHigh.setText(todayAllWeather.getNighttemp() + "°C~" + todayAllWeather.getDaytemp() + "°C");
        //获取风向和风力！
        if((todayAllWeather.getDaywind().equals(todayAllWeather.getNightwind())) && (todayAllWeather.getDaypower().equals(todayAllWeather.getNightpower()))) {
            tvWin.setText(todayBaseWeather.getWinddirection() + "风" + todayBaseWeather.getWindpower() + "级");
        }else {
            tvWin.setText(todayAllWeather.getDaywind() + "风" + todayAllWeather.getDaypower() + "级转" + todayAllWeather.getNightwind() + "风" + todayAllWeather.getNightpower() + "级");
        }
        //获取湿度！
        tvAir.setText("湿度:" + todayBaseWeather.getHumidity() + "\n" + getTipsOfWeather(todayBaseWeather.getWeather()));
        //获取天气图标！
        ivWeather.setImageResource(getImgResOfWeather(todayBaseWeather.getWeather()));
        //去掉当天的天气
        castsBeans.remove(0);
        //未来天气的展示
        mWeatherAdapter = new FutureWeatherAdapter(this, castsBeans);
        rlvFutureWeather.setAdapter(mWeatherAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rlvFutureWeather.setLayoutManager(layoutManager);
    }

    /**
     * 根据不同天气展示不同的诗句
     */
    private String getTipsOfWeather(String weaStr) {
        String result = "";
        if(Objects.equals(weaStr, "晴") || Objects.equals(weaStr, "多云")) {
            result = "水光潋滟晴方好,山色空蒙雨亦奇";
        }else if(Objects.equals(weaStr, "阴") || Objects.equals(weaStr, "雾")) {
            result = "双星何时今宵会,遗我庭前月一钩";
        } else if (Objects.equals(weaStr, "沙尘暴")) {
            result = "黄埃散漫风萧索,云栈萦纡登剑阁";
        } else {
            result = "阳光总在风雨后,乌云上有晴空";
        }
        return result;
    }

    /**
     * 根据不同的天气展示不同的图标
     */
    private int getImgResOfWeather(String weaStr) {
        int result = 0;
        switch (weaStr){
            case "晴":
                result = R.drawable.biz_plugin_weather_qing;
                break;
            case "多云":
                result = R.drawable.biz_plugin_weather_duoyun;
                break;
            case "沙尘暴":
                result = R.drawable.biz_plugin_weather_shachenbao;
                break;
            case "雾":
                result = R.drawable.biz_plugin_weather_wu;
                break;
            case "阴":
                result = R.drawable.biz_plugin_weather_yin;
                break;
            case "阵雨":
                result = R.drawable.biz_plugin_weather_zhenyu;
                break;
            case "雷阵雨":
                result = R.drawable.biz_plugin_weather_leizhenyu;
                break;
            case "雷阵雨并伴有冰雹":
                result = R.drawable.biz_plugin_weather_leizhenyubingbao;
                break;
            case "雨":
                result = R.drawable.biz_plugin_weather_xiaoyu;
                break;
            case "小雨":
                result = R.drawable.biz_plugin_weather_xiaoyu;
                break;
            case "中雨":
                result = R.drawable.biz_plugin_weather_zhongyu;
                break;
            case "大雨":
                result = R.drawable.biz_plugin_weather_dayu;
                break;
            case "暴雨":
                result = R.drawable.biz_plugin_weather_baoyu;
                break;
            case "大暴雨":
                result = R.drawable.biz_plugin_weather_dabaoyu;
                break;
            case "特大暴雨":
                result = R.drawable.biz_plugin_weather_tedabaoyu;
                break;
            case "雨夹雪":
                result = R.drawable.biz_plugin_weather_yujiaxue;
                break;
            case "阵雪":
                result = R.drawable.biz_plugin_weather_zhenxue;
                break;
            case "小雪":
                result = R.drawable.biz_plugin_weather_xiaoxue;
                break;
            case "中雪":
                result = R.drawable.biz_plugin_weather_zhongxue;
                break;
            case "大雪":
                result = R.drawable.biz_plugin_weather_daxue;
                break;
            case "暴雪":
                result = R.drawable.biz_plugin_weather_baoxue;
                break;
            default:
                result = R.drawable.biz_plugin_weather_qing;
                break;
        }
        return result;
    }

    /**
     * 判断当前是白天还是夜晚（已弃用）
     */
    private boolean DayOrNight(String reporttime) {
        int time = (int) (reporttime.charAt(12) + reporttime.charAt(13));
        if (time >= 6 && time <= 18) {
            return true;
        } else return false;
    }

    /**
     * 转换为中文日期
     */
    private String ToChineseWeek(String week) {
        switch (week) {
            case "1":
                return "星期一";
            case "2":
                return "星期二";
            case "3":
                return "星期三";
            case "4":
                return "星期四";
            case "5":
                return "星期五";
            case "6":
                return "星期六";
            default:
                return "星期日";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化方法
        initView();
    }

    private void initView() {
        //绑定下拉菜单
        mSpinner = findViewById(R.id.sp_city);
        mCities = getResources().getStringArray(R.array.cities);
        mSpAdapter = new ArrayAdapter<>(this, R.layout.sp_item_layout, mCities);
        mSpinner.setAdapter(mSpAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * 当控件被选中时
             */
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedCity = mCities[position];
                Log.d("fan", "----selectedCity----" + selectedCity);
                getWeatherOfCity(selectedCity);
            }
            /**
             * 当控件没被选中时
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        //绑定各种控件
        tvWeather = findViewById(R.id.tv_weather);
        tvAir = findViewById(R.id.tv_air);
        tvTem = findViewById(R.id.tv_tem);
        tvTemLowHigh = findViewById(R.id.tv_tem_low_high);
        rlvFutureWeather = findViewById(R.id.rlv_future_weather);
        tvWin = findViewById(R.id.tv_win);
        ivWeather = findViewById(R.id.iv_weather);
    }

    private void getWeatherOfCity(String selectedCity) {
        //开启子线程，请求网络
        new Thread(new Runnable() {
            @Override
            public void run() {
                //请求网络
                String weatherOfAllCity = NetUtil.getWeatherOfAllCity(selectedCity);
                String weatherOfBaseCity = NetUtil.getWeatherOfBaseCity(selectedCity);
                //使用handler将数据传递给主线程
                Message message = Message.obtain();
                message.what = 0;
                message.obj = weatherOfAllCity + "#" + weatherOfBaseCity;
                mHandler.sendMessage(message);
            }
        }).start();
    }
}