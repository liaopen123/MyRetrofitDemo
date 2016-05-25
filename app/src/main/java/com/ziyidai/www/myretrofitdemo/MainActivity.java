package com.ziyidai.www.myretrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.apaches.commons.codec.binary.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.ziyidai.www.myretrofitdemo.utils.MyNetUtils;


import java.io.UnsupportedEncodingException;


import retrofit.Call;
import retrofit.Callback;

import retrofit.Response;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TotalBean totalbean = new Gson().fromJson(
//                response.toString(), TotalBean.class);
//       RegisterVerifyCode

        // Define the interceptor, add authentication headers


        XZQBAPI api = MyNetUtils.getAPI();
        User1 user1 = new User1("15613575679");
        Call<TotalBean> users = api.getUsers(user1);

        users.enqueue(new Callback<TotalBean>() {
            @Override
            public void onResponse(Response<TotalBean> response) {
                RegisterVerifyCode registerVerifyCode =   response(response,RegisterVerifyCode.class);
                Log.e(TAG,"registerVerifyCode:"+registerVerifyCode.data.message);
                Log.e(TAG,"registerVerifyCode:"+registerVerifyCode.data.mobileVerifyCode);
                Log.e(TAG,"registerVerifyCode:"+registerVerifyCode.data.code);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

//        calls.enqueue(new Callback<TotalBean>() {
//
//
//            @Override
//            public void onResponse(Response<TotalBean> response) {
//                Log.e(TAG,"response.body():"+response.body());


//                Log.e(TAG,"data.message():"+bean.data.message);
//                Log.e(TAG,"response.message():"+response.message());
//                Log.e(TAG,"response.toString():"+response.toString());
//                Log.e(TAG,"response.code():"+response.code());
//                Log.e(TAG,"response.errorBody():"+response.errorBody());
//                Log.e(TAG,"response.headers():"+response.headers());
//                Log.e(TAG,"response.isSuccess():"+response.isSuccess());
//                Log.e(TAG,"response.raw().message():"+response.raw().message());
//                Log.e(TAG,"response.raw().toString():"+response.raw().toString());
//                Log.e(TAG,"response.raw().message():"+response.raw().body().toString());
//                Toast.makeText(MainActivity.this,response.message(),Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                t.printStackTrace();
//                Toast.makeText(MainActivity.this,"sb",Toast.LENGTH_LONG).show();
//            }
//        });

    }

    private <T> T response(Response<TotalBean> response,Class<T> clazz) {
        String data = decryptCode(response.body().data);
        Log.e("SYSTEMOUT", "response -> " + data.toString());
                Log.e(TAG,"response.message():"+response.message());
                Log.e(TAG,"response.toString():"+response.toString());
                Log.e(TAG,"response.code():"+response.code());
                Log.e(TAG,"response.errorBody():"+response.errorBody());
                Log.e(TAG,"response.headers():"+response.headers());
                Log.e(TAG,"response.isSuccess():"+response.isSuccess());
                Log.e(TAG,"response.raw().message():"+response.raw().message());
                Log.e(TAG,"response.raw().urlString():"+response.raw().request().urlString());
                Log.e(TAG,"response.raw().message():"+response.raw().body().toString());
        T bean = new Gson().fromJson(data.toString(),
                clazz);

        return bean;
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
