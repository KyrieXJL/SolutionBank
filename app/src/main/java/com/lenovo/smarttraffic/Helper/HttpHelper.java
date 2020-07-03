package com.lenovo.smarttraffic.Helper;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONObject;

public class HttpHelper {
    private static HttpHelper httpHelper;
    private static final String BASEURL="http://106.14.2.80:8080/ts/json/simulate/";
    private RequestQueue requestQueue;
    public HttpHelper(Context context){
        requestQueue=Volley.newRequestQueue(context.getApplicationContext());

    }

    public static HttpHelper getInstance(Context context){
        if (httpHelper==null){
            httpHelper=new HttpHelper(context);
        }

        return httpHelper;
    }

    public void PostJosn(String url, String body, Response.Listener<JSONObject> listener){
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, BASEURL + url, body, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        jsonObjectRequest.setTag("This");
        requestQueue.add(jsonObjectRequest);
    }

    public void CancleAll(){
        requestQueue.cancelAll("This");
    }
}
