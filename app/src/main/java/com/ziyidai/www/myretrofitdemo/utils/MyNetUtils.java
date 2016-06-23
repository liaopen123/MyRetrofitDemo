package com.ziyidai.www.myretrofitdemo.utils;


import com.ziyidai.www.myretrofitdemo.XZQBAPI;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


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


  public static Interceptor getInterceptorInstance(){
      synchronized (monitor) {
          if (interceptor == null) {
              interceptor = new Interceptor() {
                  @Override
                  public Response intercept(Chain chain) throws IOException {
                      Request newRequest = chain.request().newBuilder().addHeader("Accept", "application/json").addHeader("Content-Type", "application/json; charset=UTF-8").build();
                     return chain.proceed(newRequest);
                  }
              };
//              interceptor = new Interceptor() {
//                  @Override
//                  public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
//                      Request newRequest = chain.request().newBuilder().addHeader("Accept", "application/json").addHeader("Content-Type", "application/json; charset=UTF-8").build();
//                      return chain.proceed(newRequest);
//                  }
//              };
          }
          return interceptor;
      }
    }
//
//    public static OkHttpClient getOkHttpInstance(){
//        synchronized (monitor) {
//            if(client==null) {
//                client = new OkHttpClient();
//                client.interceptors().add(getInterceptorInstance());
//            }
//            return client;
//        }
//    }
    public static XZQBAPI getAPI(){
        synchronized (monitor) {
            if(retrofit==null) {
                retrofit = new Retrofit.Builder().baseUrl("http://app.xiaoziqianbao.com:9088/Cxf_xzlc_app/user/").addConverterFactory(GsonConverterFactory.create()).build();
            }
            if(service==null){
                service = retrofit.create(XZQBAPI.class);
            }

            return service;
        }
    }




}
