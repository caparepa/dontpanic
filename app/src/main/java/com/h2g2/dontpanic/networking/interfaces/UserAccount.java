package com.h2g2.dontpanic.networking.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/23
 */
public interface UserAccount {

    String USER_ACCOUNT_REGISTER="account/register";
    String USER_ACCOUNT_LOGIN="account/login";
    String USER_ACCOUNT_USE="/profile/{profileId}/use";
    String USER_ACCOUNT="account";
    String USER_ACCOUNT_KEYS ="account/{accountId}/keys";
    String USER_ACCOUNT_REFRESH ="account/refresh";
    String USER_ACCOUNT_CODE_REQUEST ="account/notification";
    String USER_ACCOUNT_RESET_PASSWORD ="account/reset";

    @Headers({"Content-Type:application/json","Accept:application/json"})
    @GET("rest/v2/name/united")
    Call<String> getCountry();

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST(USER_ACCOUNT_REGISTER)
    Call<ResponseBody> register(@Body String bean);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST(USER_ACCOUNT_LOGIN)
    Call<ResponseBody> login(@Body String bean);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST(USER_ACCOUNT_LOGIN)
    Call<ResponseBody> getActivity();

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET(USER_ACCOUNT)
    Call<ResponseBody> getAccount(@Header("Authorization") String authorization);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET(USER_ACCOUNT_KEYS)
    Call<ResponseBody> getKeys(@Header("Authorization") String authorization, @Path("accountId") String accountId);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST(USER_ACCOUNT_KEYS)
    Call<ResponseBody> saveKeys(@Header("Authorization") String authorization, @Path("accountId") String accountId, @Body String bean);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST(USER_ACCOUNT_REFRESH)
    Call<ResponseBody> refresh(@Header("Authorization") String authorization);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST(USER_ACCOUNT_CODE_REQUEST)
    Call<ResponseBody> getCodeChangePass(@Body String bean);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST(USER_ACCOUNT_RESET_PASSWORD)
    Call<ResponseBody> resetPassword(@Header("Authorization") String authorization,@Body String bean);

}
