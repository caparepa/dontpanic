package com.h2g2.dontpanic.networking.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/26
 */
public interface Profile {
    final String PROFILE="profile";

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST(PROFILE+"/{profileId}/use")
    Call<ResponseBody> setProfile(@Header("Authorization") String authorization, @Path("profileId") String profileId);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST(PROFILE)
    Call<ResponseBody> save(@Header("Authorization") String authorization,  @Body String bean);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET(PROFILE+"/{profileId}")
    Call<ResponseBody> getProfile(@Header("Authorization") String authorization, @Path("profileId") String profileId);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET(PROFILE+"/{profileId}/channel/{channelId}")
    Call<ResponseBody> getProfileStatus(@Header("Authorization") String authorization, @Path("profileId") String profileId, @Path("channelId") String channelId);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST(PROFILE+"/{profileId}/channel/{channelId}/energy")
    Call<ResponseBody> setProfileEnergy(@Header("Authorization") String authorization, @Path("profileId") String profileId, @Path("channelId") String channelId,@Body String bean);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @PUT(PROFILE+"/{profileId}")
    Call<ResponseBody> update(@Header("Authorization") String authorization, @Path("profileId") String profileId, @Body String bean);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @PUT(PROFILE+"/{profileId}")
    Call<ResponseBody> updateSteps(@Header("Authorization") String authorization, @Path("profileId") String profileId);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @PUT("account/password")
    Call<ResponseBody> updatePassword(@Header("Authorization") String authorization, @Body String body);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @HTTP(method = "DELETE", path = PROFILE+"/{profileId}", hasBody = true)
    Call<ResponseBody> deleteProfile(@Header("Authorization") String authorization, @Path("profileId") String profileId, @Body String body);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @DELETE("tracker/{trackerId}")
    Call<ResponseBody> deleteTracker(@Header("Authorization") String authorization, @Path("trackerId") String trackerId);
}
