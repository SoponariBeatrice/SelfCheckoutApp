package com.example.selfcheckout;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {
    @POST("/api/auth/signup")
    Call<User> registerUser(@Body User user);

}