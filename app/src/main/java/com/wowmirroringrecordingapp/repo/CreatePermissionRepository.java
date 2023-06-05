package com.wowmirroringrecordingapp.repo;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.wowmirroringrecordingapp.networks.ApiClient;
import com.wowmirroringrecordingapp.networks.ApiError;
import com.wowmirroringrecordingapp.networks.TransWorldApi;
import com.wowmirroringrecordingapp.pojo.permission.CreatePermissionData;
import com.wowmirroringrecordingapp.pojo.permission.CreatePermissionResponse;
import com.wowmirroringrecordingapp.pojo.permission.CreatePermissionResponseData;
import com.wowmirroringrecordingapp.pojo.permission.GetPermissionResponse;
import com.wowmirroringrecordingapp.pojo.permission.GetPermissionResponseData;
import com.wowmirroringrecordingapp.sqlite.App;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePermissionRepository {
    TransWorldApi covidApi;

    public CreatePermissionRepository() {
        covidApi = ApiClient.getClient().create(TransWorldApi.class);

    }

    public MutableLiveData<CreatePermissionResponse> createPermissionByWarden(CreatePermissionData createPermissionData){
        MutableLiveData<CreatePermissionResponse> createPermissionResponseMutableLiveData = new MutableLiveData<>();
        Call<JsonObject> call = covidApi.createPermission(createPermissionData);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    CreatePermissionResponseData signUps = new Gson().fromJson(response.body().get("data"), CreatePermissionResponseData.class);
                    List<CreatePermissionResponseData> signUps1 = new ArrayList<>();
                    signUps1.add(signUps);
                    CreatePermissionResponse permissionResponse = new CreatePermissionResponse();
                    permissionResponse.setStatus(true);
                    permissionResponse.setData(signUps1);
                    permissionResponse.setMessage("Permission has been created");
                    createPermissionResponseMutableLiveData.setValue(permissionResponse);
                } else {
                    CreatePermissionResponse permissionResponse = new CreatePermissionResponse();
                    permissionResponse.setStatus(false);
                    try {
                        permissionResponse.setMessage(ApiError.getApiError(response, App.getContext()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    createPermissionResponseMutableLiveData.setValue(permissionResponse);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                CreatePermissionResponse permissionResponse = new CreatePermissionResponse();
                permissionResponse.setStatus(false);
                permissionResponse.setMessage(t.getMessage());
                createPermissionResponseMutableLiveData.setValue(permissionResponse);
            }
        });
        return createPermissionResponseMutableLiveData;
    }

    public MutableLiveData<CreatePermissionResponse> createPermission(CreatePermissionData loginData) {
        MutableLiveData<CreatePermissionResponse> responseMutableLiveData = new MutableLiveData<>();
        Call<JsonObject> call = covidApi.createPermission(loginData);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    CreatePermissionResponseData signUps = new Gson().fromJson(response.body().get("data"), CreatePermissionResponseData.class);
                    List<CreatePermissionResponseData> signUps1 = new ArrayList<>();
                    signUps1.add(signUps);
                    CreatePermissionResponse permissionResponse = new CreatePermissionResponse();
                    permissionResponse.setStatus(true);
                    permissionResponse.setData(signUps1);
                    permissionResponse.setMessage("Permission has been created");
                    responseMutableLiveData.setValue(permissionResponse);
                } else {
                    CreatePermissionResponse permissionResponse = new CreatePermissionResponse();
                    permissionResponse.setStatus(false);
                    try {
                        permissionResponse.setMessage(ApiError.getApiError(response, App.getContext()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    responseMutableLiveData.setValue(permissionResponse);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                CreatePermissionResponse permissionResponse = new CreatePermissionResponse();
                permissionResponse.setStatus(false);
                permissionResponse.setMessage(t.getMessage());
                responseMutableLiveData.setValue(permissionResponse);
            }
        });

        return responseMutableLiveData;

    }

    public MutableLiveData<GetPermissionResponse> getPermission(String student_id) {
        MutableLiveData<GetPermissionResponse> responseMutableLiveData = new MutableLiveData<>();
        Call<JsonObject> call = covidApi.getPermission(student_id);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    GetPermissionResponseData signUps = new Gson().fromJson(response.body().get("data"), GetPermissionResponseData.class);
                    List<GetPermissionResponseData> signUps1 = new ArrayList<>();
                    signUps1.add(signUps);
                    GetPermissionResponse getPermissionResponse = new GetPermissionResponse();
                    getPermissionResponse.setStatus(true);
                    getPermissionResponse.setData(signUps1);
                    getPermissionResponse.setMessage("Permission Granted");
                    responseMutableLiveData.setValue(getPermissionResponse);
                } else {
                    GetPermissionResponse getPermissionResponse = new GetPermissionResponse();
                    getPermissionResponse.setStatus(false);
                    try {
                        getPermissionResponse.setMessage(ApiError.getApiError(response, App.getContext()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    responseMutableLiveData.setValue(getPermissionResponse);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                GetPermissionResponse getPermissionResponse = new GetPermissionResponse();
                getPermissionResponse.setStatus(false);
                getPermissionResponse.setMessage(t.getMessage());
                responseMutableLiveData.setValue(getPermissionResponse);
            }
        });

        return responseMutableLiveData;

    }

}
