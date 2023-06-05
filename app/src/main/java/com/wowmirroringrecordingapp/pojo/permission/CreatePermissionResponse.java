package com.wowmirroringrecordingapp.pojo.permission;

import java.util.List;

public class CreatePermissionResponse {
    private List<CreatePermissionResponseData> data;
    private String message;
    private boolean status;

    public List<CreatePermissionResponseData> getData() {
        return data;
    }

    public void setData(List<CreatePermissionResponseData> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
