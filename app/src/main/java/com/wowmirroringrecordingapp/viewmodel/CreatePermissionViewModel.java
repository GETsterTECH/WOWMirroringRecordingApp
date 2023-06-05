package com.wowmirroringrecordingapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.wowmirroringrecordingapp.pojo.permission.CreatePermissionData;
import com.wowmirroringrecordingapp.pojo.permission.CreatePermissionResponse;
import com.wowmirroringrecordingapp.pojo.permission.GetPermissionResponse;
import com.wowmirroringrecordingapp.repo.CreatePermissionRepository;

public class CreatePermissionViewModel extends AndroidViewModel {
    CreatePermissionRepository createPermissionRepository;

    public CreatePermissionViewModel(@NonNull Application application) {
        super(application);
        createPermissionRepository = new CreatePermissionRepository();
    }
    public LiveData<CreatePermissionResponse> createPermission(CreatePermissionData createPermissionData){
        return createPermissionRepository.createPermissionByWarden(createPermissionData);
    }

    public LiveData<GetPermissionResponse> getPermission(String student_id){
        return createPermissionRepository.getPermission(student_id);
    }
}
