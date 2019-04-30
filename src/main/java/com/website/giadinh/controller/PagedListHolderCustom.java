package com.website.giadinh.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public abstract class PagedListHolderCustom<T> {
	public abstract void pagedListHolder(HttpServletRequest request, List<T> list, Integer p);
}
