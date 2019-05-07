package com.website.giadinh.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public abstract class PageController<T> {
	public abstract void pagedListHolder(HttpServletRequest request, List<T> list, Integer p);
	
	public abstract String getReturnPage(Integer p);
	
	public abstract String getReturnPage(String k, Integer p);
}