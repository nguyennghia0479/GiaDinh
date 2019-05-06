package com.website.giadinh.entity;

public enum Thu {
	THU_HAI("THU HAI"),
	THU_BA("THU BA"),
	THU_TU("THU TU"),
	THU_NAM("THU NAM"),
	THU_SAU("THU_SAU"),
	THU_BAY("THU_BAY");
	
	String thu;
	
	private Thu(String thu) {
		this.thu = thu;
	}

	public String getThu() {
		return thu;
	}	
}