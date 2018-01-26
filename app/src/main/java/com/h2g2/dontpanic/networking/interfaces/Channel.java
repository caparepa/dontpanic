package com.h2g2.dontpanic.networking.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/26
 */
public interface Channel {
    String CHANNEL="channel";

    @Headers({"Accept:application/json"})
    @GET(CHANNEL)
    Call<ResponseBody> getChannels(@Header("Authorization") String authorization);

    @GET(CHANNEL)
    Call<ResponseBody> getAllChannels(@Query("size") String size);

    @Headers({"Accept:application/json"})
    @GET(CHANNEL)
    Call<ResponseBody> getChannels(@Header("Authorization") String authorization, @Query("size") String size);

    @Headers({"Accept:application/json"})
    @GET(CHANNEL+"/{channelId}")
    Call<ResponseBody> getChannelById(@Header("Authorization") String authorization, @Path("channelId") int channelId);


    public String getSize();
}
