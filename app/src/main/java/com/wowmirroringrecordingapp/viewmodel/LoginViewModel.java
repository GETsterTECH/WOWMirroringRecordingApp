package com.wowmirroringrecordingapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.wowmirroringrecordingapp.pojo.login.LoginData;
import com.wowmirroringrecordingapp.pojo.login.LoginResponse;
import com.wowmirroringrecordingapp.repo.LoginRepository;

public class LoginViewModel extends AndroidViewModel {
    LoginRepository loginRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginRepository = new LoginRepository();
    }
    public LiveData<LoginResponse> loginResponseLiveData(LoginData loginData){
        return loginRepository.loginUser(loginData);
    }
}
