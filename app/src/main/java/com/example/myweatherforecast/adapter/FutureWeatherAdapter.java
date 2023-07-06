package com.example.myweatherforecast.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherforecast.R;
import com.example.myweatherforecast.bean.CastsBean;
import com.example.myweatherforecast.bean.ForecastsBean;
import com.example.myweatherforecast.bean.WeatherBean;

import java.util.List;

public class FutureWeatherAdapter extends RecyclerView.Adapter<FutureWeatherAdapter.WeatherViewHolder> {

    private Context mContext;
    private List<CastsBean> mCastsBean;

    public FutureWeatherAdapter(Context mContext, List<CastsBean> mCastsBean) {
        this.mContext = mContext;
        this.mCastsBean = mCastsBean;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.weather_item_layout, parent, false);
        WeatherViewHolder weatherViewHolder = new WeatherViewHolder(view);
        return weatherViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        CastsBean castsBean = mCastsBean.get(position);

        holder.tvWeather.setText(castsBean.getDayweather());
        holder.tvTem.setText(castsBean.getDaytemp() + "°C");
        holder.tvDate.setText(castsBean.getDate() + ToChineseWeek(castsBean.getWeek()));
        holder.tvTemLowHigh.setText(castsBean.getNighttemp() + "°C~" + castsBean.getDaytemp() + "°C");
        holder.tvWin.setText(castsBean.getDaywind() + "风" +castsBean.getDaypower() + "级");
        holder.ivWeather.setImageResource(getImgResOfWeather(castsBean.getDayweather()));


    }

    @Override
    public int getItemCount() {
//        if (mWeatherBeans == null) {
//            return 0;
//        }
        return (mCastsBean == null) ? 0 : mCastsBean.size();
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder {
        TextView tvWeather, tvTem, tvTemLowHigh, tvWin, tvAir, tvDate;
        ImageView ivWeather;
        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);

            tvWeather = itemView.findViewById(R.id.tv_weather);
            tvAir = itemView.findViewById(R.id.tv_air);
            tvTem = itemView.findViewById(R.id.tv_tem);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvTemLowHigh = itemView.findViewById(R.id.tv_tem_low_high);
            tvWin = itemView.findViewById(R.id.tv_win);
            ivWeather = itemView.findViewById(R.id.iv_weather);
        }
    }

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

}
