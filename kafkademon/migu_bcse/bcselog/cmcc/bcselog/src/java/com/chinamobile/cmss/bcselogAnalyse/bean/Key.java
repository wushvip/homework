package com.chinamobile.cmss.bcselogAnalyse.bean;

public class Key {

	
	private String user_id;
	private String app_id;
	private String flag;

	public Key(String user_id, String app_id, String flag) {
		
		this.user_id = user_id;
		this.app_id = app_id;
		this.flag = flag;
	}

	public boolean isEmpty() {

		if ( user_id != null && user_id != "" && app_id != null && app_id != ""
				&& flag != null && flag != "")
			return false;

		return true;
	}

	

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		if (((Key) obj).getApp_id().equals(app_id) && ((Key) obj).getUser_id().equals(user_id)
				&& ((Key) obj).getFlag().equals(flag) ) {
			return true;
		}
		return false;

	}

	@Override
	public int hashCode() {
		return user_id.hashCode() + app_id.hashCode() + flag.hashCode();

	}

	@Override
	public String toString() {
		return  user_id + " " + app_id + " " + flag;
	}

}
