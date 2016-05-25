package com.ziyidai.www.myretrofitdemo.utils;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.ziyidai.www.myretrofitdemo.XZQBAPI;

import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Pony on 2016/3/23.
 */
public class MyNetUtils {
    protected static final Object monitor = new Object();//保证单利 防止锁
    private static Interceptor interceptor;
    private static OkHttpClient client;
    private static Retrofit retrofit;
    private static XZQBAPI service;

    public void  init(){

    }


  public static Interceptor  getInterceptorInstance(){
      synchronized (monitor) {
          if (interceptor == null) {
              interceptor = new Interceptor() {
                  @Override
                  public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                      Request newRequest = chain.request().newBuilder().addHeader("Accept", "application/json").addHeader("Content-Type", "application/json; charset=UTF-8").build();
                      return chain.proceed(newRequest);
                  }
              };
          }
          return interceptor;
      }
    }

    public static OkHttpClient getOkHttpInstance(){
        synchronized (monitor) {
            if(client==null) {
                client = new OkHttpClient();
                client.interceptors().add(getInterceptorInstance());
            }
            return client;
        }
    }
    public static XZQBAPI getAPI(){
        synchronized (monitor) {
            if(retrofit==null) {
                retrofit = new Retrofit.Builder().baseUrl("http://app.xiaoziqianbao.com:9088/Cxf_xzlc_app/user/").addConverterFactory(GsonConverterFactory.create()).client(getOkHttpInstance()).build();
            }
            if(service==null){
                service = retrofit.create(XZQBAPI.class);
            }

            return service;
        }
    }




}
