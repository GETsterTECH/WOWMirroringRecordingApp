package com.wowmirroringrecordingapp.repo;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.wowmirroringrecordingapp.networks.ApiClient;
import com.wowmirroringrecordingapp.networks.ApiError;
import com.wowmirroringrecordingapp.networks.TransWorldApi;
import com.wowmirroringrecordingapp.pojo.login.Data;
import com.wowmirroringrecordingapp.pojo.login.LoginData;
import com.wowmirroringrecordingapp.pojo.login.LoginResponse;
import com.wowmirroringrecordingapp.sqlite.App;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    TransWorldApi covidApi;

    public LoginRepository() {
        covidApi = ApiClient.getClient().create(TransWorldApi.class);

    }

    public MutableLiveData<LoginResponse> loginUser(LoginData loginData) {
        MutableLiveData<LoginResponse> loginResponseMutableLiveData = new MutableLiveData<>();
        Call<JsonObject> call = covidApi.loginUser(loginData);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {

//                    LoginResponseData signUps = new Gson().fromJson(response.body(), LoginResponseData.class);
//                    List<LoginResponseData> signUps1 = new ArrayList<>();
//                    signUps1.add(signUps);

                    Data signUps = new Gson().fromJson(response.body().get("data"), Data.class);
                    List<Data> signUps1 = new ArrayList<>();
                    signUps1.add(signUps);

                    LoginResponse loginResponse = new LoginResponse();
                    loginResponse.setStatus(true);
                    loginResponse.setData(signUps1);
                    loginResponse.setMessage("User login successful!");
                    loginResponseMutableLiveData.setValue(loginResponse);

//                    LoginResponse loginResponse = new Gson().fromJson(response.body(),
//                            LoginResponse.class);
//                    loginResponse.setStatus(true);
//                    loginResponseMutableLiveData.setValue(loginResponse);
                } else {
                    LoginResponse loginResponse = new LoginResponse();
                    loginResponse.setStatus(false);
                    try {
                        loginResponse.setMessage(ApiError.getApiError(response, App.getContext()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    loginResponseMutableLiveData.setValue(loginResponse);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setStatus(false);
                loginResponse.setMessage(t.getMessage());
                loginResponseMutableLiveData.setValue(loginResponse);
            }
        });

        return loginResponseMutableLiveData;

    }
}
