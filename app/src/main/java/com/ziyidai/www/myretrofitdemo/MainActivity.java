package com.ziyidai.www.myretrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;

import java.util.logging.Logger;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private User service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TotalBean totalbean = new Gson().fromJson(
//                response.toString(), TotalBean.class);
//        String data = decryptCode(totalbean.data);
//        Logger.d("SYSTEMOUT", "response -> " + data.toString());
//        RegisterVerifyCode bean = null;
//        bean = new Gson().fromJson(data.toString(),
//                RegisterVerifyCode.class);

      Retrofit retrofit =  new Retrofit.Builder().baseUrl("http://api.nuuneoi.com/base/").addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(User.class);
        service.getUsers("15613575679");
    }
}
