package com.project2.mediciERS.message;

public class ResponseFile {
	private String id;
	private String name;
	private String url;
	private String type;
	private long size;
	private int rbId;

	public ResponseFile(String id, String name, String url, String type, long size, int rbId) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.type = type;
		this.size = size;
		this.rbId = rbId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public int getRbId() {
		return rbId;
	}

	public void setRbId(int rbId) {
		this.rbId = rbId;
	}

}
