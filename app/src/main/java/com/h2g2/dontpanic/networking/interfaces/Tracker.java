package com.h2g2.dontpanic.networking.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/26
 */
public interface Tracker {
    final String TRACKER="tracker";

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET(TRACKER)
    Call<ResponseBody> getTrackerList(@Header("Authorization") String authorization);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST(TRACKER)
    Call<ResponseBody> savetTracker(@Header("Authorization") String authorization, @Body String bean);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET(TRACKER+"/{trackerID}")
    Call<ResponseBody> getTracker(@Header("Authorization") String authorization,@Path("trackerID") String trackerID);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @PUT(TRACKER+"/{trackerID}")
    Call<ResponseBody> updateTracker(@Header("Authorization") String authorization,@Path("trackerID") String trackerID, @Body String bean);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @DELETE(TRACKER+"/{trackerID}")
    Call<ResponseBody> deleteTracker(@Header("Authorization") String authorization,@Path("trackerID") String trackerID);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @GET(TRACKER+"/availability")
    Call<ResponseBody> getAvailabilityTracker(@Header("Authorization") String authorization,@Query("tracker_factory_id")  String trackerID);
}
