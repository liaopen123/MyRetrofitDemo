package com.ziyidai.www.myretrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.logging.Logger;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
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

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.nuuneoi.com/base/").addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(User.class);
        Call<TotalBean> calls = service.getUsers("15613575679");
        calls.enqueue(new Callback<TotalBean>() {


            @Override
            public void onResponse(Response<TotalBean> response) {
                Toast.makeText(MainActivity.this,response.message(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(MainActivity.this,"sb",Toast.LENGTH_LONG).show();
            }
        });

    }
}
