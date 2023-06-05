package com.wowmirroringrecordingapp.pojo.permission;

public class CreatePermissionResponseData{
	private String permissionForReason;
	private int acutalInDateTime;
	private String dateTimeIn;
	private int cheakbyWatchmenId;
	private int watchmenVarifyDateTime;
	private String wardenId;
	private String permission;
	private String dateTimeOut;
	private String studentRegid;
	private String permissionCreateTimeDate;

	public String getPermissionForReason(){
		return permissionForReason;
	}

	public int getAcutalInDateTime(){
		return acutalInDateTime;
	}

	public String getDateTimeIn(){
		return dateTimeIn;
	}

	public int getCheakbyWatchmenId(){
		return cheakbyWatchmenId;
	}

	public int getWatchmenVarifyDateTime(){
		return watchmenVarifyDateTime;
	}

	public String getWardenId(){
		return wardenId;
	}

	public String getPermission(){
		return permission;
	}

	public String getDateTimeOut(){
		return dateTimeOut;
	}

	public String getStudentRegid(){
		return studentRegid;
	}

	public String getPermissionCreateTimeDate(){
		return permissionCreateTimeDate;
	}
}
