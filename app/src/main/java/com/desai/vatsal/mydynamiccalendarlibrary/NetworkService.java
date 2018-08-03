package com.desai.vatsal.mydynamiccalendarlibrary;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NetworkService {
    @GET("getworkflows")
    Call<LoginResponse> getworkflows(
            @Query("token") String token);

    @GET("getevents")
    Call<LoginResponseEvents> getevents(
            @Query("token") String token,
            @Query("WFID") int WFID);
}