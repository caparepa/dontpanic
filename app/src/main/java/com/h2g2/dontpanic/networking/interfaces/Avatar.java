package com.h2g2.dontpanic.networking.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/26
 */
public interface Avatar {
    final String AVATAR="avatar";

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET(AVATAR)
    Call<ResponseBody> getAvatars(@Header("Authorization") String authorization);

    @GET(AVATAR)
    Call<ResponseBody> getAvatars();

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET(AVATAR+"/{avatar}")
    Call<ResponseBody> getAvatar(@Header("Authorization") String authorization,@Path("avatar") String avatar);
}
