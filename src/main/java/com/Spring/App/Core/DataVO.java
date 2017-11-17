package com.Spring.App.Core;

import java.util.List;

/*
 * Auther:xujk 20170920
 * jquery table ajax result class
 * */
public class DataVO<T> {
	 private int draw; // Client request times
	 private int recordsTotal; // Total records number without conditions
	 
	 private int recordsFiltered; // Total records number with conditions
	 private List<T> data; // The data we should display on the page
	 
	 public int getdraw()
	 {return draw;}
	 public void setdraw(int value)
	 {
		 draw=value;
	 }

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
