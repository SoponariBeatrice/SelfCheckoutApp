package com.example.selfcheckout;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUser {
    public static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(){
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://78.97.175.107:8090")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}