package com.wowmirroringrecordingapp.pojo.permission;

public class CreatePermissionData{
	private String permission_for_reason;
	private String date_time_in;
	private String warden_id;
	private String permission;
	private String date_time_out;
	private String student_regid;
	private String permission_create_time_date;

	public String getPermission_for_reason() {
		return permission_for_reason;
	}

	public void setPermission_for_reason(String permission_for_reason) {
		this.permission_for_reason = permission_for_reason;
	}

	public String getDate_time_in() {
		return date_time_in;
	}

	public void setDate_time_in(String date_time_in) {
		this.date_time_in = date_time_in;
	}

	public String getWarden_id() {
		return warden_id;
	}

	public void setWarden_id(String warden_id) {
		this.warden_id = warden_id;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getDate_time_out() {
		return date_time_out;
	}

	public void setDate_time_out(String date_time_out) {
		this.date_time_out = date_time_out;
	}

	public String getStudent_regid() {
		return student_regid;
	}

	public void setStudent_regid(String student_regid) {
		this.student_regid = student_regid;
	}

	public String getPermission_create_time_date() {
		return permission_create_time_date;
	}

	public void setPermission_create_time_date(String permission_create_time_date) {
		this.permission_create_time_date = permission_create_time_date;
	}
}
