package com.lenovo.smarttraffic.Helper;

import android.content.Context;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class TimerHelper {
    private Context context;
    private static TimerHelper timerHelper;

    public TimerHelper(Context context) {
        this.context = context;
    }

    public static TimerHelper getInstance(Context context){
        if (timerHelper==null){
            timerHelper=new TimerHelper(context);
        }
        return timerHelper;
    }

    public Timer starTimer(final String url,final String params,int period,final UpdateUI updateUI){
        final  HttpHelper httpHelper=HttpHelper.getInstance(context);
        Timer timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                httpHelper.PostJosn(url, params, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        updateUI.update(jsonObject);
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask,0,period*1000);
        return timer;
    }
    public interface UpdateUI{
        void update(JSONObject object);
    }
}
