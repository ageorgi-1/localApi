package com.minnt.exampleproject;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.minnt.localapi.LocalService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService extends Application {

    private static ApiService instance;
    private static String SERVER_ADDRESS = "https://jsonplaceholder.typicode.com/" ;

    @Override
    public void onCreate() {
        instance = this ;
        super.onCreate();
    }




    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    okhttp3.Response response = chain.proceed(request);

                    Log.e("STATUSCODE_RESPONSE", response.code() + "");

                    if (response.code() == 500) {


                        return response;
                    }

                    return response;
                }
            })

            .build();



    private  static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(SERVER_ADDRESS);


    public static <S> S createService(Class<S> serviceClass) {

        final Gson gson = new GsonBuilder()
                .create();

        Retrofit retrofit = builder
                .client(okHttpClient)
                .client(LocalService.LocalApiBuilder(instance))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(serviceClass);
    }






}
