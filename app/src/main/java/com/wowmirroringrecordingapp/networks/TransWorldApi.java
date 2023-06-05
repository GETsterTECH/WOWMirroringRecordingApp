package com.wowmirroringrecordingapp.networks;

import com.google.gson.JsonObject;
import com.wowmirroringrecordingapp.pojo.login.LoginData;
import com.wowmirroringrecordingapp.pojo.permission.CreatePermissionData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TransWorldApi {

//    @FormUrlEncoded
//    @Headers({"Content-Type: application/x-www-form-urlencoded",
//            "Authorization: Basic aW50ZWxsaWh1YjppbnRlbGxpaHVi"})
    @POST(ApiUrls.LOGIN_URL)
    Call<JsonObject> loginUser(@Body LoginData loginData);

    @POST(ApiUrls.CREATE_PERMISSION_BY_WARDEN)
    Call<JsonObject> createPermission(@Body CreatePermissionData loginData);

    @GET(ApiUrls.GET_PERMISSION)
    Call<JsonObject> getPermission(@Query("roll") String student_id);

//    @GET(ApiUrls.GET_COVID_INFO)
//    Call<JsonObject> getCovidInfo(@Query("patientId") String token);

}
