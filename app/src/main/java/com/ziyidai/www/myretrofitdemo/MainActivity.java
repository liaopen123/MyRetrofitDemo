package com.ziyidai.www.myretrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.apaches.commons.codec.binary.Base64;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.logging.Logger;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private User service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TotalBean totalbean = new Gson().fromJson(
//                response.toString(), TotalBean.class);
//       RegisterVerifyCode

        // Define the interceptor, add authentication headers
        Interceptor interceptor = new Interceptor() {
            @Override
            public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder().addHeader("Accept", "application/json").addHeader("Content-Type", "application/json; charset=UTF-8").build();
                return chain.proceed(newRequest);
            }
        };


        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(interceptor);


        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://app.xiaoziqianbao.com:9088/Cxf_xzlc_app/user/").addConverterFactory(GsonConverterFactory.create()).client(client).build();
        service = retrofit.create(User.class);
        HashMap<String,String> params = new HashMap();
        params.put("phone","15613575679");
        User1 user1 = new User1("15613575679");
        Call<TotalBean> calls = service.getUsers(user1);
        calls.enqueue(new Callback<TotalBean>() {


            @Override
            public void onResponse(Response<TotalBean> response) {
                Log.e(TAG,"response.body():"+response.body());

                String data = decryptCode(response.body().data);
                Log.e("SYSTEMOUT", "response -> " + data.toString());
                RegisterVerifyCode bean = null;
                bean = new Gson().fromJson(data.toString(),
                        RegisterVerifyCode.class);
                Log.e(TAG,"data.message():"+bean.data.message);
                Log.e(TAG,"response.message():"+response.message());
                Log.e(TAG,"response.toString():"+response.toString());
                Log.e(TAG,"response.code():"+response.code());
                Log.e(TAG,"response.errorBody():"+response.errorBody());
                Log.e(TAG,"response.headers():"+response.headers());
                Log.e(TAG,"response.isSuccess():"+response.isSuccess());
                Log.e(TAG,"response.raw().message():"+response.raw().message());
                Log.e(TAG,"response.raw().toString():"+response.raw().toString());
                Log.e(TAG,"response.raw().message():"+response.raw().body().toString());
                Toast.makeText(MainActivity.this,response.message(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Toast.makeText(MainActivity.this,"sb",Toast.LENGTH_LONG).show();
            }
        });

    }





    public String decryptCode(String ret) {
        Base64 base642 = new Base64();
        byte[] res = base642.decode(ret.getBytes());
        String srt2;
        try {
            // UTF-8 GB2312
            srt2 = new String(res, "UTF-8");
            return srt2;
        } catch (UnsupportedEncodingException e) {
            Log.i(TAG, "解码失败");
            e.printStackTrace();
            return "";
        }
    }
}
