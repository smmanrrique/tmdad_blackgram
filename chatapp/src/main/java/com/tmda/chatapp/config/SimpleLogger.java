package com.tmda.chatapp.config;

import java.util.Date;

public class SimpleLogger {
	private String prefix;
	private Boolean showDate;
	
	public SimpleLogger() {
		prefix = "DefaultPrefix> ";
		showDate = true;
	}
	
	public SimpleLogger(String prefix, Boolean showDate) {
		this.prefix = prefix;
		this.showDate = showDate;
	}

	public String log (String msg) {
		String date = getDate();
		return prefix + date + "> " + msg;
	}
	
	private String getDate () {
		return showDate?new Date().toString():"";
	}
	
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public Boolean getShowDate() {
		return showDate;
	}
	public void setShowDate(Boolean showDate) {
		this.showDate = showDate;
	}
	
}
