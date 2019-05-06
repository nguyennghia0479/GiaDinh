package com.website.giadinh.entity;

public enum Ca {
	CA_MOT("CA MOT"), 
	CA_HAI("CA HAI"), 
	CA_BA("CA BA"), 
	CA_BON("CA BON");

	String ca;

	private Ca(String ca) {
		this.ca = ca;
	}

	public String getCa() {
		return ca;
	}
}